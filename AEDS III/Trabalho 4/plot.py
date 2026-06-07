import os
import pandas as pd
import matplotlib.pyplot as plt

# garante pasta
os.makedirs("Results/graficos", exist_ok=True)

# leitura segura
df = pd.read_csv("Results/TodosResultados.txt")
df = df.dropna()

df["Algoritmo"] = df["Algoritmo"].astype(str)
df["Tamanho"] = df["Tamanho"].astype(int)
df["TempoExecucao"] = df["TempoExecucao"].astype(float)
df["Qualidade"] = df["Qualidade"].astype(float)

# ordena para gráficos não ficarem bagunçados
df = df.sort_values(by=["Tamanho"])

# =========================================================
# 1) POR TAMANHO - TEMPO
# =========================================================
for size in sorted(df["Tamanho"].unique()):

    plt.figure()

    sub = df[df["Tamanho"] == size]

    for alg in sub["Algoritmo"].unique():
        d = sub[sub["Algoritmo"] == alg].reset_index(drop=True)
        plt.plot(range(len(d)), d["TempoExecucao"], marker="o", label=alg)

    plt.title(f"Tempo - Instância {size}")
    plt.xlabel("Execuções")
    plt.ylabel("Tempo (s)")
    plt.legend()
    plt.grid()

    plt.savefig(f"Results/graficos/tempo_{size}.png", dpi=300)
    plt.close()

# =========================================================
# 2) POR TAMANHO - FITNESS
# =========================================================
for size in sorted(df["Tamanho"].unique()):

    plt.figure()

    sub = df[df["Tamanho"] == size]

    for alg in sub["Algoritmo"].unique():
        d = sub[sub["Algoritmo"] == alg].reset_index(drop=True)
        plt.plot(range(len(d)), d["Qualidade"], marker="o", label=alg)

    plt.title(f"Fitness - Instância {size}")
    plt.xlabel("Execuções")
    plt.ylabel("Fitness")
    plt.legend()
    plt.grid()

    plt.savefig(f"Results/graficos/fitness_{size}.png", dpi=300)
    plt.close()

# =========================================================
# 3) GLOBAL - TEMPO
# =========================================================
plt.figure()

for alg in df["Algoritmo"].unique():
    sub = df[df["Algoritmo"] == alg].sort_values("Tamanho")
    plt.plot(sub["Tamanho"], sub["TempoExecucao"], marker="o", label=alg)

plt.xscale("log")
plt.yscale("log")
plt.title("Comparação Global - Tempo")
plt.xlabel("Tamanho da instância")
plt.ylabel("Tempo (s)")
plt.legend()
plt.grid()

plt.savefig("Results/graficos/comparacao_global_tempo.png", dpi=300)
plt.close()

# =========================================================
# 4) GLOBAL - FITNESS
# =========================================================
plt.figure()

for alg in df["Algoritmo"].unique():
    sub = df[df["Algoritmo"] == alg].sort_values("Tamanho")
    plt.plot(sub["Tamanho"], sub["Qualidade"], marker="o", label=alg)

plt.xscale("log")
plt.title("Comparação Global - Fitness")
plt.xlabel("Tamanho da instância")
plt.ylabel("Fitness")
plt.legend()
plt.grid()

plt.savefig("Results/graficos/comparacao_global_fitness.png", dpi=300)
plt.close()

print("Gráficos gerados em Results/graficos/")