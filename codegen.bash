set -e
cd "$(dirname "$0")"
cat > code.mx
java -cp ./lib/antlr-4.8-complete.jar:./bin Main --codegen
#llc --march=riscv32 -mattr=+m ./code_opt.ll -o output.s
#llc --march=riscv32 -mattr=+m ./builtin.ll -o builtin.s -O3
#cat output.s