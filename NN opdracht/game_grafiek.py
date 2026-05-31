import matplotlib.pyplot as plt
import numpy as np

# Data
experiments = ['Exp 1: Baseline', 'Exp 2: Undersampling', 'Exp 3: Feature Eng.']
train_scores = [90, 84, 86]
test_scores = [88, 82, 85]
survival_times = [8, 12, 45]

fig, (ax1, ax2) = plt.subplots(1, 2, figsize=(14, 6))

# Grafiek 1: Train vs Test
x = np.arange(len(experiments))
width = 0.35
ax1.bar(x - width/2, train_scores, width, label='Train Accuracy', color='skyblue', edgecolor='black')
ax1.bar(x + width/2, test_scores, width, label='Test Accuracy', color='salmon', edgecolor='black')
ax1.set_ylabel('Accuracy (%)', fontsize=12)
ax1.set_title('Vergelijking tussen Train en Test Scores', fontsize=14, fontweight='bold')
ax1.set_xticks(x)
ax1.set_xticklabels(experiments, fontsize=11)
ax1.set_ylim(0, 100)
ax1.legend()
ax1.grid(axis='y', linestyle='--', alpha=0.7)

# Grafiek 2: Survival Time
bars = ax2.bar(experiments, survival_times, color='lightgreen', edgecolor='black')
ax2.set_ylabel('Tijd (Seconden)', fontsize=12)
ax2.set_title('Prestaties van het Model in de Game', fontsize=14, fontweight='bold')
ax2.tick_params(axis='x', labelsize=11)
ax2.grid(axis='y', linestyle='--', alpha=0.7)

for bar in bars:
    yval = bar.get_height()
    ax2.text(bar.get_x() + bar.get_width()/2, yval + 1, f'{yval} sec', ha='center', va='bottom', fontweight='bold')

plt.tight_layout()
plt.savefig('experiment_grafieken.png', dpi=300)
print("Grafikler 'experiment_grafieken.png' olarak kaydedildi!")