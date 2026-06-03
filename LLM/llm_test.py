from openai import OpenAI
import time

# Connecting to LM Studio's local server (Port: 1234)
client = OpenAI(base_url="http://localhost:1234/v1", api_key="lm-studio")

# 3 different prompts to ask the model
prompts = [
    "Wat is de hoofdstad van Frankrijk?",
    "Leg in één korte zin uit wat een variabele is in programmeren.",
    "Wat is 5 + 7?"
]

print("--- Start Python API Test ---")

# Starting a loop for each prompt
for i, prompt in enumerate(prompts):
    start_tijd = time.time()
    
    # Sending request to LM Studio
    response = client.chat.completions.create(
        model="local-model", # LM Studio automatically detects the loaded model (Qwen)
        messages=[
            {"role": "user", "content": prompt}
        ],
        temperature=0.7
    )
    
    eind_tijd = time.time()
    
    # Collecting the required metadata
    antwoord = response.choices[0].message.content
    model_naam = response.model
    lengte_antwoord = len(antwoord)
    tijdsduur = round(eind_tijd - start_tijd, 2)
    
    # Printing to console
    print(f"\n[Prompt {i+1}]: {prompt}")
    print(f"[Response]: {antwoord}")
    print(f"[Metadata] -> Model: {model_naam} | Tijd: {tijdsduur} sec | Lengte: {lengte_antwoord} tekens")

print("\n--- Test Voltooid ---")