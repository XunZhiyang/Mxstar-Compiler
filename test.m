int Wallace = 1 << 10;

class sometimes {
    int naive;
    void make_money() {
        this.naive++;
    }
}

int main() {
    sometimes keep = new sometimes;
    keep.naive = 0;
    while (getInt() < Wallace) {
        keep.make_money();
    }
    return 0;
}