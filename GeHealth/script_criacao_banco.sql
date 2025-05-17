-- Criação do banco de dados
CREATE DATABASE projetogehealth;
USE projetogehealth;

-- Tabela de Especialidades
CREATE TABLE especialidade (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- Tabela de Pacientes
CREATE TABLE paciente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    data_nascimento DATE NOT NULL,
    email VARCHAR(100),
    telefone VARCHAR(20)
);

-- Tabela de Profissionais de Saúde
CREATE TABLE profissional_saude (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    data_nascimento DATE NOT NULL,
    registro_profissional VARCHAR(50) NOT NULL,
    tipo VARCHAR(50),
    especialidade_id INT,
    email VARCHAR(100),
    telefone VARCHAR(20),
    FOREIGN KEY (especialidade_id) REFERENCES especialidade(id)
);

-- Tabela de Consultas (referenciando paciente e médico por chave estrangeira)
CREATE TABLE consulta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    profissional_saude_id INT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data_consulta DATE NOT NULL,
    observacao TEXT,
    especialidade_id INT,
    FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    FOREIGN KEY (profissional_saude_id) REFERENCES profissional_saude(id),
    FOREIGN KEY (especialidade_id) REFERENCES especialidade(id)
);

-- Tabela de Usuários
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    nivel VARCHAR(20) NOT NULL
);
