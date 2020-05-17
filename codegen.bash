set -e
cd "$(dirname "$0")"
cat > code.mx
java -cp ./lib/antlr-4.8-complete.jar:./bin Main code.mx
llc --march=riscv32 -mattr=+m ./code.ll -o code.s
