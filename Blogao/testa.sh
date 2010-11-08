#!/bin/bash

#compila os arquivos de teste
ant compilaTestes
echo "Compilando testes..."
cd src
javac ../RodaTestes.java
cd ..
mv RodaTestes.class build

#executa os testes
echo "Rodando testes..."
cd build
java RodaTestes
cd ..

#limpa os arquivos usados somente para os testes
ant limpaTestes

