# --- IMPORTS ---
import pandas as pd
import numpy as np

# Stap 1: Extract (Data Inlezen)
# We gebruiken nu etmgeg_344.txt (Rotterdam Airport)
df_knmi = pd.read_csv('PowerBI Leercoaching/etmgeg_344.txt', skiprows=51, skipinitialspace=True, on_bad_lines='skip')

# Stap 2: Transform (Data Schoonmaken en Voorbereiden)
# Selecteer de kolommen die we nodig hebben
columns_to_keep = ['YYYYMMDD', 'TG', 'TN', 'TX', 'RH']
df_clean = df_knmi[columns_to_keep].copy()

# Hernoem de kolommen naar logische Engelse/Nederlandse namen
df_clean.columns = ['Date', 'Gem_Temp', 'Min_Temp', 'Max_Temp', 'Neerslag']

# FIX: 'errors=coerce' zorgt ervoor dat lege rijen in 'NaT' (Not a Time) veranderen zonder te crashen
df_clean['Date'] = pd.to_datetime(df_clean['Date'].astype(str).str.strip(), format='%Y%m%d', errors='coerce')

# Verwijder alle rijen met missende of kapotte data
df_clean = df_clean.dropna()

# KNMI gebruikt '-1' voor neerslag (regen) minder dan 0.05mm. We veranderen dit naar 0.
df_clean['Neerslag'] = df_clean['Neerslag'].replace(-1, 0)

# KNMI levert temperaturen en neerslag aan in 0.1 eenheden (bijv. 255 = 25.5 graden).
# We delen de kolommen door 10 om echte Celsius en Millimeters te krijgen.
cols_to_divide = ['Gem_Temp', 'Min_Temp', 'Max_Temp', 'Neerslag']
df_clean[cols_to_divide] = df_clean[cols_to_divide] / 10.0

# Check het resultaat
print(f"✅ Data is succesvol getransformeerd! Totaal aantal rijen: {len(df_clean)}")
print(df_clean.head())

# Stap 3: Load (Opslaan voor Power BI)
# We slaan de schone data op als een nieuwe CSV
df_clean.to_csv('knmi_clean_data.csv', index=False)
print("✅ Bestand 'knmi_clean_data.csv' is succesvol opgeslagen en klaar voor Power BI!")