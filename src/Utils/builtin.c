#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void print(char *str) {
    printf("%s", str);
}

void println(char *str) {
    printf("%s\n", str);
}

void printInt(int n) {
    printf("%d", n);
}

void printlnInt(int n) {
    printf("%d\n", n);
}

char* getString() {
    char* s = (char *) malloc(256);
    scanf("%s", s);
    return s;
}

int getInt() {
    int res;
    scanf("%d", &res);
    return res;
}

char* toString(int n) {
    int len = 0;
    len += n == 0 ? 1 : 0;
    int t = n;
    while (t != 0) {
        t /= 10;
        len++;
    }

    len += n < 0 ? 1 : 0;

    char *s = (char *) malloc(len + 1);
    s[len] = '\0';

    int beg = 0;
    if (n < 0) {
        n = -n;
        s[beg++] = '-';
    }

	int i;
    for (i = len - 1; i >= beg; i--) {
        s[i] = n % 10 + '0';
        n /= 10;
    }
    return s;
}

int string__length(char *this) {
    return strlen(this);
}

char* string__substring(char *this, int left, int right) {
    char *substring = (char *) malloc(right - left + 1);
    for (int i = left; i < right; ++i) {
        substring[i - left] = this[i];
    }
    substring[right - left] = '\0';
    return substring;
}

int string__parseInt(char *this) {
    int sgn = 1, i = 0, res = 0;
    if (this[0] == '-') {
        sgn = -1;
        i = 1;
    }
    for (; this[i] >= '0' && this[i] <= '9'; ++i) {
        res = res * 10 + this[i] - '0';
    }
    return sgn * res;
}

int string__ord(char *this, int pos) {
    return this[pos];
}

char* _string_concatenate(char *s1, char *s2) {
    int l1 = strlen(s1);
    int l2 = strlen(s2);
    char *res = (char*) malloc(l1 + l2 + 1);
    for (int i = 0; i < l1; i++) {
        res[i] = s1[i];
    }
    for (int i = 0; i < l2; i++) {
        res[l1 + i] = s2[i];
    }
    res[l1 + l2] = '\0';
    return res;
}

char _string_eq(char *a, char *b) {
    return strcmp(a, b) == 0;
}

char _string_ne(char *a, char *b) {
    return strcmp(a, b) != 0;
}

char _string_slt(char *a, char *b) {
    return strcmp(a, b) < 0;
}

char _string_sle(char *a, char *b) {
    return strcmp(a, b) <= 0;
}

char _string_sgt(char *a, char *b) {
    return strcmp(a, b) > 0;
}

char _string_sge(char *a, char *b) {
    return strcmp(a, b) >= 0;
}
