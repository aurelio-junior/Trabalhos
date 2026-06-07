CC = gcc

CFLAGS = -Wall -Wextra -O2 -Iinclude
LDFLAGS = -lm

SRC = $(filter-out src/instancias.c, $(wildcard src/*.c))

TARGET = bin/main
GENERATOR = bin/instancias

VENV = venv
PY = $(VENV)/bin/python
PIP = $(VENV)/bin/pip

# ==========================================================
# DEFAULT
# ==========================================================
all: setup instancias run plot

# ==========================================================
# VENV (não recria se já existir)
# ==========================================================
setup:
	@if [ ! -d "$(VENV)" ]; then \
		echo "Criando ambiente virtual..."; \
		python3 -m venv $(VENV); \
		$(PIP) install --upgrade pip; \
		$(PIP) install numpy pandas matplotlib; \
	else \
		echo "venv já existe"; \
	fi

# ==========================================================
# COMPILAÇÃO PRINCIPAL
# ==========================================================
$(TARGET): $(SRC)
	@mkdir -p bin
	$(CC) $(CFLAGS) $(SRC) -o $(TARGET) $(LDFLAGS)

run: $(TARGET)
	./run_prompt.sh
	
# ==========================================================
# GERADOR DE INSTÂNCIAS
# ==========================================================
instancias: $(GENERATOR)
	./$(GENERATOR)

$(GENERATOR): src/instancias.c
	@mkdir -p bin
	$(CC) $(CFLAGS) src/instancias.c -o $(GENERATOR) $(LDFLAGS)

# ==========================================================
# PLOT AUTOMÁTICO
# ==========================================================
plot: setup
	@echo "Gerando gráficos..."
	@mkdir -p Results/graficos
	$(PY) plot.py

# ==========================================================
# LIMPEZA SEGURA
# ==========================================================
clean:
	@echo "Limpando projeto (sem apagar venv)..."
	rm -rf bin instances Results

# limpeza total opcional
clean-all:
	@echo "Limpando TUDO (incluindo venv)..."
	rm -rf bin instances Results venv

rebuild: clean all