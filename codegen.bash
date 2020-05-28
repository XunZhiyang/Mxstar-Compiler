set -e
cd "$(dirname "$0")"
cat > code.mx
java -cp ./lib/antlr-4.8-complete.jar:./bin Main
#llc --march=riscv32 -mattr=+m ./code_opt.ll -o output.s
cat output.s