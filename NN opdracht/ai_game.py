import pygame
import random
import sys
import os
import math
import numpy as np
from keras.models import load_model
import warnings

# Ignore TensorFlow/Keras warnings in the terminal for a cleaner output
warnings.filterwarnings('ignore')

# --- LOAD THE TRAINED AI (BULLETPROOF PATH) ---
print("Loading AI Brain...")

# Get the exact folder where this Python script is located
current_folder = os.path.dirname(os.path.abspath(__file__))

# Combine that folder path with our model's filename
model_path = os.path.join(current_folder, 'ai_player.keras')

try:
    # Load the model using the absolute path
    model = load_model(model_path)
    print("✅ AI Loaded Successfully! Starting game...")
except Exception as e:
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

# Colors
WHITE = (255, 255, 255)
BLACK = (20, 20, 20)
BLUE = (50, 150, 255)
RED = (255, 75, 75)
LIGHT_GRAY = (200, 200, 200)

screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("AI Dodging Game - AUTOPILOT MODE")
clock = pygame.time.Clock()

# Game Variables
player_size = 50
player_x = WIDTH // 2
player_y = HEIGHT - player_size - 20

rocks = []
rock_size = 40
frame_count = 0
running = True
game_over = False

# --- REAL-TIME CLOCK SETUP ---
# Get the exact millisecond when the game starts
start_ticks = pygame.time.get_ticks()
score_seconds = 0

# --- MAIN GAME LOOP ---
while running:
    # 1. Event Handling
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    if not game_over:
        # --- REAL-TIME SCORE CALCULATION ---
        # Calculate real elapsed seconds using the system clock (independent of FPS)
        score_seconds = (pygame.time.get_ticks() - start_ticks) // 1000

        # 2. Spawn Falling Rocks
        frame_count += 1
        if frame_count % ROCK_SPAWN_RATE == 0:
            rock_x = random.randint(0, WIDTH - rock_size)
            rock_y = -rock_size
            rocks.append([rock_x, rock_y])

        # 3. Update Rocks & Detect Collisions
        closest_rock = None
        min_distance = 99999 

        for rock in rocks[:]:
            rock[1] += ROCK_SPEED 
            
            # Axis-Aligned Bounding Box (AABB) Collision Detection
            if (player_x < rock[0] + rock_size and 
                player_x + player_size > rock[0] and 
                player_y < rock[1] + rock_size and 
                player_y + player_size > rock[1]):
                game_over = True
            
            # Remove rocks off-screen
            if rock[1] > HEIGHT:
                rocks.remove(rock)
            else:
                # TRUE THREAT DETECTION: Focus on the rock with the shortest direct distance
                distance = math.hypot(player_x - rock[0], player_y - rock[1])
                if distance < min_distance:
                    min_distance = distance
                    closest_rock = rock

        # 4. AI DECISION MAKING
        if closest_rock:
            # FEATURE ENGINEERING: Calculate the horizontal distance
            delta_x = player_x - closest_rock[0]
            
            # Package the current game state (4 features) and normalize by 800.0
            ai_input = np.array([[player_x, closest_rock[0], closest_rock[1], delta_x]]) / 800.0
            
            # Get prediction probabilities from the Neural Network
            predictions = model.predict(ai_input, verbose=0)
            
            # Select the action with the highest probability (0=Left, 1=Stay, 2=Right)
            best_action = np.argmax(predictions[0])
            
            # Execute the AI's chosen move
            if best_action == 0 and player_x > 0:
                player_x -= PLAYER_SPEED
            elif best_action == 2 and player_x < WIDTH - player_size:
                player_x += PLAYER_SPEED

    # 5. Render Graphics (View Layer)
    screen.fill(BLACK)
    
    if not game_over:
        # Draw Player and Rocks
        pygame.draw.rect(screen, BLUE, (player_x, player_y, player_size, player_size))
        for rock in rocks:
            pygame.draw.rect(screen, RED, (rock[0], rock[1], rock_size, rock_size))
            
        # --- SCORE RENDERING ---
        # Display the real-time seconds on the top-left corner
        score_font = pygame.font.SysFont("Arial", 24, bold=True)
        score_text = score_font.render(f"Score (Overlevingstijd): {score_seconds} sec", True, WHITE)
        screen.blit(score_text, (10, 10))
        
    else:
        # Draw Game Over Text
        font = pygame.font.SysFont("Arial", 60, bold=True)
        text = font.render("AI FAILED", True, WHITE)
        screen.blit(text, (WIDTH//2 - text.get_width()//2, HEIGHT//2 - text.get_height()//2))
        
        # Draw Final Score (Frozen at the exact moment of death)
        final_score_font = pygame.font.SysFont("Arial", 30)
        final_score_text = final_score_font.render(f"Final Score: {score_seconds} seconden", True, LIGHT_GRAY)
        screen.blit(final_score_text, (WIDTH//2 - final_score_text.get_width()//2, HEIGHT//2 + 50))

    pygame.display.flip()
    clock.tick(FPS)

pygame.quit()