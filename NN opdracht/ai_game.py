import pygame
import random
import sys
import os
import numpy as np
# Import load_model to bring our trained AI brain into the game
from keras.models import load_model
import warnings
warnings.filterwarnings('ignore')

# --- LOAD THE TRAINED AI (BULLETPROOF PATH) ---
print("Loading AI Brain...")

# 1. Get the exact folder where this Python script is located
current_folder = os.path.dirname(os.path.abspath(__file__))

# 2. Combine that folder path with our model's filename
model_path = os.path.join(current_folder, 'ai_player.keras')

try:
    # 3. Load the model using the absolute path
    model = load_model(model_path)
    print("✅ AI Loaded Successfully! Starting game...")
except Exception as e:
    # If it fails, print the exact error and the path it tried to check
    print(f"❌ Error loading model: {e}")
    print(f"🔍 I looked for the model exactly here: {model_path}")
    sys.exit()

# --- GAME SETUP & CONSTANTS ---
pygame.init()
WIDTH = 600
HEIGHT = 800
FPS = 60
PLAYER_SPEED = 7
ROCK_SPEED = 6
ROCK_SPAWN_RATE = 25

WHITE = (255, 255, 255)
BLACK = (20, 20, 20)
BLUE = (50, 150, 255)
RED = (255, 75, 75)

screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("AI Dodging Game - AUTOPILOT MODE")
clock = pygame.time.Clock()

player_size = 50
player_x = WIDTH // 2
player_y = HEIGHT - player_size - 20

rocks = []
rock_size = 40
frame_count = 0
running = True
game_over = False

# --- MAIN GAME LOOP ---
while running:
    # 1. Event Handling
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    if not game_over:
        # 2. Spawn Falling Rocks
        frame_count += 1
        if frame_count % ROCK_SPAWN_RATE == 0:
            rock_x = random.randint(0, WIDTH - rock_size)
            rock_y = -rock_size
            rocks.append([rock_x, rock_y])

        # 3. Update Rocks & Detect Collisions
        import math
        closest_rock = None
        min_distance = 99999 

        for rock in rocks[:]:
            rock[1] += ROCK_SPEED 
            
            # Collision Detection
            if (player_x < rock[0] + rock_size and 
                player_x + player_size > rock[0] and 
                player_y < rock[1] + rock_size and 
                player_y + player_size > rock[1]):
                game_over = True
            
            # Remove rocks off-screen
            if rock[1] > HEIGHT:
                rocks.remove(rock)
            else:
                # AI artık dikkati dağılmadan sadece kendine en yakın taşa odaklanıyor!
                distance = math.hypot(player_x - rock[0], player_y - rock[1])
                if distance < min_distance:
                    min_distance = distance
                    closest_rock = rock

        # 4. AI DECISION MAKING (The Magic Happens Here)
        if closest_rock:
            # Yapay zekaya aradaki net mesafeyi de hesaplayıp veriyoruz
            delta_x = player_x - closest_rock[0]
            
            # DİKKAT: ai_input içine delta_x'i de ekledik (Artık 4 parça veri gidiyor)
            ai_input = np.array([[player_x, closest_rock[0], closest_rock[1], delta_x]]) / 800.0
            
            predictions = model.predict(ai_input, verbose=0)
            best_action = np.argmax(predictions[0])
            
            if best_action == 0 and player_x > 0:
                player_x -= PLAYER_SPEED
            elif best_action == 2 and player_x < WIDTH - player_size:
                player_x += PLAYER_SPEED

    # 5. Render Graphics
    screen.fill(BLACK)
    
    if not game_over:
        pygame.draw.rect(screen, BLUE, (player_x, player_y, player_size, player_size))
        for rock in rocks:
            pygame.draw.rect(screen, RED, (rock[0], rock[1], rock_size, rock_size))
    else:
        font = pygame.font.SysFont("Arial", 60, bold=True)
        text = font.render("AI FAILED", True, WHITE)
        screen.blit(text, (WIDTH//2 - text.get_width()//2, HEIGHT//2 - text.get_height()//2))

    pygame.display.flip()
    clock.tick(FPS)

pygame.quit()