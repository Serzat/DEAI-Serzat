import pygame
import random
import csv
import sys
import math

# --- GAME SETUP & CONSTANTS ---
pygame.init()

# Screen dimensions
WIDTH = 600
HEIGHT = 800
FPS = 60

# Game physics and speeds
PLAYER_SPEED = 7
ROCK_SPEED = 6
ROCK_SPAWN_RATE = 25  # Lower means rocks spawn faster

# Colors for the View Layer
WHITE = (255, 255, 255)
BLACK = (20, 20, 20)
BLUE = (50, 150, 255)
RED = (255, 75, 75)

# Initialize screen and clock
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("AI Dodging Game - Data Collector")
clock = pygame.time.Clock()

# --- DATA LOGGING SETUP (MODEL LAYER) ---
CSV_FILE = "train_data.csv"

# Create or overwrite the CSV file and write the header row
# Notice we added 'Delta_X' for our Feature Engineering!
with open(CSV_FILE, mode='w', newline='') as file:
    writer = csv.writer(file)
    writer.writerow(["Player_X", "Closest_Rock_X", "Closest_Rock_Y", "Delta_X", "Action_Taken"])

# --- GAME VARIABLES ---
player_size = 50
player_x = WIDTH // 2
player_y = HEIGHT - player_size - 20

rocks = []
rock_size = 40
frame_count = 0

running = True
game_over = False

print("Game Started! Use LEFT and RIGHT arrows to dodge. Data is being recorded...")

# --- MAIN GAME LOOP ---
while running:
    # 1. Event Handling (Quit Game)
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    if not game_over:
        # 2. Player Input (Action)
        keys = pygame.key.get_pressed()
        action = 1  # Default action: 1 (Stay/Do nothing)
        
        # Move Left (Action: 0)
        if keys[pygame.K_LEFT] and player_x > 0:
            player_x -= PLAYER_SPEED
            action = 0
            
        # Move Right (Action: 2)
        elif keys[pygame.K_RIGHT] and player_x < WIDTH - player_size:
            player_x += PLAYER_SPEED
            action = 2

        # 3. Spawn Falling Rocks
        frame_count += 1
        if frame_count % ROCK_SPAWN_RATE == 0:
            rock_x = random.randint(0, WIDTH - rock_size)
            rock_y = -rock_size
            rocks.append([rock_x, rock_y])

        # 4. Update Rocks & Detect Collisions
        closest_rock = None
        min_distance = 99999  # Start with an impossibly large distance

        for rock in rocks[:]:
            rock[1] += ROCK_SPEED  # Move rock down
            
            # Axis-Aligned Bounding Box (AABB) Collision Detection
            if (player_x < rock[0] + rock_size and 
                player_x + player_size > rock[0] and 
                player_y < rock[1] + rock_size and 
                player_y + player_size > rock[1]):
                game_over = True
                print("Game Over! You hit a rock.")
            
            # Remove rocks that fall off the screen
            if rock[1] > HEIGHT:
                rocks.remove(rock)
            else:
                # TRUE THREAT DETECTION: Calculate the actual Euclidean distance (Pythagorean theorem)
                distance = math.hypot(player_x - rock[0], player_y - rock[1])
                
                # We want to record the rock that is physically closest to the player
                if distance < min_distance:
                    min_distance = distance
                    closest_rock = rock

        # 5. Write Data to CSV (Background Logic)
        if closest_rock:
            # FEATURE ENGINEERING: Calculate the horizontal distance between player and rock
            delta_x = player_x - closest_rock[0]
            
            with open(CSV_FILE, mode='a', newline='') as file:
                writer = csv.writer(file)
                # Save: [Player_X, Rock_X, Rock_Y, Delta_X, Target_Action]
                writer.writerow([player_x, closest_rock[0], closest_rock[1], delta_x, action])

    # 6. Render Graphics (View Layer)
    screen.fill(BLACK)
    
    if not game_over:
        # Draw Player
        pygame.draw.rect(screen, BLUE, (player_x, player_y, player_size, player_size))
        # Draw Rocks
        for rock in rocks:
            pygame.draw.rect(screen, RED, (rock[0], rock[1], rock_size, rock_size))
    else:
        # Draw Game Over Text
        font = pygame.font.SysFont("Arial", 60, bold=True)
        text = font.render("GAME OVER", True, WHITE)
        screen.blit(text, (WIDTH//2 - text.get_width()//2, HEIGHT//2 - text.get_height()//2))
        
        sub_font = pygame.font.SysFont("Arial", 24)
        sub_text = sub_font.render("Close the window to stop.", True, (150, 150, 150))
        screen.blit(sub_text, (WIDTH//2 - sub_text.get_width()//2, HEIGHT//2 + 50))

    pygame.display.flip()
    clock.tick(FPS)

pygame.quit()
sys.exit()