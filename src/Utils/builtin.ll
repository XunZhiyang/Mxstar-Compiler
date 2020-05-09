
@.str = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.1 = private unnamed_addr constant [4 x i8] c"%s\0A\00", align 1
@.str.2 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

define void @print(i8* %0) {
  %2 = alloca i8*, align 8
  store i8* %0, i8** %2, align 8
  %3 = load i8*, i8** %2, align 8
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i8* %3)
  ret void
}

declare i32 @printf(i8*, ...)

define void @println(i8* %0) {
  %2 = alloca i8*, align 8
  store i8* %0, i8** %2, align 8
  %3 = load i8*, i8** %2, align 8
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.1, i64 0, i64 0), i8* %3)
  ret void
}

define void @printInt(i32 %0) {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32 %3)
  ret void
}

define void @printlnInt(i32 %0) {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3, i64 0, i64 0), i32 %3)
  ret void
}

define i8* @getString() {
  %1 = alloca i8*, align 8
  %2 = call noalias i8* @malloc(i64 256)
  store i8* %2, i8** %1, align 8
  %3 = load i8*, i8** %1, align 8
  %4 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i8* %3)
  %5 = load i8*, i8** %1, align 8
  ret i8* %5
}

declare noalias i8* @malloc(i64)

declare i32 @__isoc99_scanf(i8*, ...)

define i32 @getInt() {
  %1 = alloca i32, align 4
  %2 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32* %1)
  %3 = load i32, i32* %1, align 4
  ret i32 %3
}

define i8* @toString(i32 %0) {
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i8*, align 8
  %6 = alloca i32, align 4
  %7 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  store i32 0, i32* %3, align 4
  %8 = load i32, i32* %2, align 4
  %9 = icmp eq i32 %8, 0
  %10 = zext i1 %9 to i64
  %11 = select i1 %9, i32 1, i32 0
  %12 = load i32, i32* %3, align 4
  %13 = add nsw i32 %12, %11
  store i32 %13, i32* %3, align 4
  %14 = load i32, i32* %2, align 4
  store i32 %14, i32* %4, align 4
  br label %15

15:
  %16 = load i32, i32* %4, align 4
  %17 = icmp ne i32 %16, 0
  br i1 %17, label %18, label %23

18:
  %19 = load i32, i32* %4, align 4
  %20 = sdiv i32 %19, 10
  store i32 %20, i32* %4, align 4
  %21 = load i32, i32* %3, align 4
  %22 = add nsw i32 %21, 1
  store i32 %22, i32* %3, align 4
  br label %15

23:
  %24 = load i32, i32* %2, align 4
  %25 = icmp slt i32 %24, 0
  %26 = zext i1 %25 to i64
  %27 = select i1 %25, i32 1, i32 0
  %28 = load i32, i32* %3, align 4
  %29 = add nsw i32 %28, %27
  store i32 %29, i32* %3, align 4
  %30 = load i32, i32* %3, align 4
  %31 = add nsw i32 %30, 1
  %32 = sext i32 %31 to i64
  %33 = call noalias i8* @malloc(i64 %32) #4
  store i8* %33, i8** %5, align 8
  %34 = load i8*, i8** %5, align 8
  %35 = load i32, i32* %3, align 4
  %36 = sext i32 %35 to i64
  %37 = getelementptr inbounds i8, i8* %34, i64 %36
  store i8 0, i8* %37, align 1
  store i32 0, i32* %6, align 4
  %38 = load i32, i32* %2, align 4
  %39 = icmp slt i32 %38, 0
  br i1 %39, label %40, label %48

40:
  %41 = load i32, i32* %2, align 4
  %42 = sub nsw i32 0, %41
  store i32 %42, i32* %2, align 4
  %43 = load i8*, i8** %5, align 8
  %44 = load i32, i32* %6, align 4
  %45 = add nsw i32 %44, 1
  store i32 %45, i32* %6, align 4
  %46 = sext i32 %44 to i64
  %47 = getelementptr inbounds i8, i8* %43, i64 %46
  store i8 45, i8* %47, align 1
  br label %48

48:
  %49 = load i32, i32* %3, align 4
  %50 = sub nsw i32 %49, 1
  store i32 %50, i32* %7, align 4
  br label %51

51:
  %52 = load i32, i32* %7, align 4
  %53 = load i32, i32* %6, align 4
  %54 = icmp sge i32 %52, %53
  br i1 %54, label %55, label %69

55:
  %56 = load i32, i32* %2, align 4
  %57 = srem i32 %56, 10
  %58 = add nsw i32 %57, 48
  %59 = trunc i32 %58 to i8
  %60 = load i8*, i8** %5, align 8
  %61 = load i32, i32* %7, align 4
  %62 = sext i32 %61 to i64
  %63 = getelementptr inbounds i8, i8* %60, i64 %62
  store i8 %59, i8* %63, align 1
  %64 = load i32, i32* %2, align 4
  %65 = sdiv i32 %64, 10
  store i32 %65, i32* %2, align 4
  br label %66

66:
  %67 = load i32, i32* %7, align 4
  %68 = add nsw i32 %67, -1
  store i32 %68, i32* %7, align 4
  br label %51

69:
  %70 = load i8*, i8** %5, align 8
  ret i8* %70
}


define i32 @string.length(i8* %0) {
  %2 = alloca i8*, align 8
  store i8* %0, i8** %2, align 8
  %3 = load i8*, i8** %2, align 8
  %4 = call i64 @strlen(i8* %3) #5
  %5 = trunc i64 %4 to i32
  ret i32 %5
}

declare i64 @strlen(i8*)

define i8* @string.substring(i8* %0, i32 %1, i32 %2) {
  %4 = alloca i8*, align 8
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca i8*, align 8
  %8 = alloca i32, align 4
  store i8* %0, i8** %4, align 8
  store i32 %1, i32* %5, align 4
  store i32 %2, i32* %6, align 4
  %9 = load i32, i32* %6, align 4
  %10 = load i32, i32* %5, align 4
  %11 = sub nsw i32 %9, %10
  %12 = add nsw i32 %11, 1
  %13 = sext i32 %12 to i64
  %14 = call noalias i8* @malloc(i64 %13)
  store i8* %14, i8** %7, align 8
  %15 = load i32, i32* %5, align 4
  store i32 %15, i32* %8, align 4
  br label %16

16:
  %17 = load i32, i32* %8, align 4
  %18 = load i32, i32* %6, align 4
  %19 = icmp slt i32 %17, %18
  br i1 %19, label %20, label %35

20:
  %21 = load i8*, i8** %4, align 8
  %22 = load i32, i32* %8, align 4
  %23 = sext i32 %22 to i64
  %24 = getelementptr inbounds i8, i8* %21, i64 %23
  %25 = load i8, i8* %24, align 1
  %26 = load i8*, i8** %7, align 8
  %27 = load i32, i32* %8, align 4
  %28 = load i32, i32* %5, align 4
  %29 = sub nsw i32 %27, %28
  %30 = sext i32 %29 to i64
  %31 = getelementptr inbounds i8, i8* %26, i64 %30
  store i8 %25, i8* %31, align 1
  br label %32

32:
  %33 = load i32, i32* %8, align 4
  %34 = add nsw i32 %33, 1
  store i32 %34, i32* %8, align 4
  br label %16

35:
  %36 = load i8*, i8** %7, align 8
  %37 = load i32, i32* %6, align 4
  %38 = load i32, i32* %5, align 4
  %39 = sub nsw i32 %37, %38
  %40 = sext i32 %39 to i64
  %41 = getelementptr inbounds i8, i8* %36, i64 %40
  store i8 0, i8* %41, align 1
  %42 = load i8*, i8** %7, align 8
  ret i8* %42
}

define i32 @string.parseInt(i8* %0) {
  %2 = alloca i8*, align 8
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  store i8* %0, i8** %2, align 8
  store i32 1, i32* %3, align 4
  store i32 0, i32* %4, align 4
  store i32 0, i32* %5, align 4
  %6 = load i8*, i8** %2, align 8
  %7 = getelementptr inbounds i8, i8* %6, i64 0
  %8 = load i8, i8* %7, align 1
  %9 = sext i8 %8 to i32
  %10 = icmp eq i32 %9, 45
  br i1 %10, label %11, label %12

11:
  store i32 -1, i32* %3, align 4
  store i32 1, i32* %4, align 4
  br label %12

12:
  br label %13

13:
  %14 = load i8*, i8** %2, align 8
  %15 = load i32, i32* %4, align 4
  %16 = sext i32 %15 to i64
  %17 = getelementptr inbounds i8, i8* %14, i64 %16
  %18 = load i8, i8* %17, align 1
  %19 = sext i8 %18 to i32
  %20 = icmp sge i32 %19, 48
  br i1 %20, label %21, label %29

21:
  %22 = load i8*, i8** %2, align 8
  %23 = load i32, i32* %4, align 4
  %24 = sext i32 %23 to i64
  %25 = getelementptr inbounds i8, i8* %22, i64 %24
  %26 = load i8, i8* %25, align 1
  %27 = sext i8 %26 to i32
  %28 = icmp sle i32 %27, 57
  br label %29

29:
  %30 = phi i1 [ false, %13 ], [ %28, %21 ]
  br i1 %30, label %31, label %45

31:
  %32 = load i32, i32* %5, align 4
  %33 = mul nsw i32 %32, 10
  %34 = load i8*, i8** %2, align 8
  %35 = load i32, i32* %4, align 4
  %36 = sext i32 %35 to i64
  %37 = getelementptr inbounds i8, i8* %34, i64 %36
  %38 = load i8, i8* %37, align 1
  %39 = sext i8 %38 to i32
  %40 = add nsw i32 %33, %39
  %41 = sub nsw i32 %40, 48
  store i32 %41, i32* %5, align 4
  br label %42

42:
  %43 = load i32, i32* %4, align 4
  %44 = add nsw i32 %43, 1
  store i32 %44, i32* %4, align 4
  br label %13

45:
  %46 = load i32, i32* %3, align 4
  %47 = load i32, i32* %5, align 4
  %48 = mul nsw i32 %46, %47
  ret i32 %48
}

define i32 @string.ord(i8* %0, i32 %1) {
  %3 = alloca i8*, align 8
  %4 = alloca i32, align 4
  store i8* %0, i8** %3, align 8
  store i32 %1, i32* %4, align 4
  %5 = load i8*, i8** %3, align 8
  %6 = load i32, i32* %4, align 4
  %7 = sext i32 %6 to i64
  %8 = getelementptr inbounds i8, i8* %5, i64 %7
  %9 = load i8, i8* %8, align 1
  %10 = sext i8 %9 to i32
  ret i32 %10
}

define i8* @_string_concatenate(i8* %0, i8* %1) {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca i8*, align 8
  %8 = alloca i32, align 4
  %9 = alloca i32, align 4
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %10 = load i8*, i8** %3, align 8
  %11 = call i64 @strlen(i8* %10) #5
  %12 = trunc i64 %11 to i32
  store i32 %12, i32* %5, align 4
  %13 = load i8*, i8** %4, align 8
  %14 = call i64 @strlen(i8* %13) #5
  %15 = trunc i64 %14 to i32
  store i32 %15, i32* %6, align 4
  %16 = load i32, i32* %5, align 4
  %17 = load i32, i32* %6, align 4
  %18 = add nsw i32 %16, %17
  %19 = add nsw i32 %18, 1
  %20 = sext i32 %19 to i64
  %21 = call noalias i8* @malloc(i64 %20)
  store i8* %21, i8** %7, align 8
  store i32 0, i32* %8, align 4
  br label %22

22:
  %23 = load i32, i32* %8, align 4
  %24 = load i32, i32* %5, align 4
  %25 = icmp slt i32 %23, %24
  br i1 %25, label %26, label %39

26:
  %27 = load i8*, i8** %3, align 8
  %28 = load i32, i32* %8, align 4
  %29 = sext i32 %28 to i64
  %30 = getelementptr inbounds i8, i8* %27, i64 %29
  %31 = load i8, i8* %30, align 1
  %32 = load i8*, i8** %7, align 8
  %33 = load i32, i32* %8, align 4
  %34 = sext i32 %33 to i64
  %35 = getelementptr inbounds i8, i8* %32, i64 %34
  store i8 %31, i8* %35, align 1
  br label %36

36:
  %37 = load i32, i32* %8, align 4
  %38 = add nsw i32 %37, 1
  store i32 %38, i32* %8, align 4
  br label %22

39:
  store i32 0, i32* %9, align 4
  br label %40

40:
  %41 = load i32, i32* %9, align 4
  %42 = load i32, i32* %6, align 4
  %43 = icmp slt i32 %41, %42
  br i1 %43, label %44, label %59

44:
  %45 = load i8*, i8** %4, align 8
  %46 = load i32, i32* %9, align 4
  %47 = sext i32 %46 to i64
  %48 = getelementptr inbounds i8, i8* %45, i64 %47
  %49 = load i8, i8* %48, align 1
  %50 = load i8*, i8** %7, align 8
  %51 = load i32, i32* %5, align 4
  %52 = load i32, i32* %9, align 4
  %53 = add nsw i32 %51, %52
  %54 = sext i32 %53 to i64
  %55 = getelementptr inbounds i8, i8* %50, i64 %54
  store i8 %49, i8* %55, align 1
  br label %56

56:
  %57 = load i32, i32* %9, align 4
  %58 = add nsw i32 %57, 1
  store i32 %58, i32* %9, align 4
  br label %40

59:
  %60 = load i8*, i8** %7, align 8
  %61 = load i32, i32* %5, align 4
  %62 = load i32, i32* %6, align 4
  %63 = add nsw i32 %61, %62
  %64 = sext i32 %63 to i64
  %65 = getelementptr inbounds i8, i8* %60, i64 %64
  store i8 0, i8* %65, align 1
  %66 = load i8*, i8** %7, align 8
  ret i8* %66
}

define i1 @_string_eq(i8* %0, i8* %1) {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #5
  %8 = icmp eq i32 %7, 0
  ret i1 %8
}

declare i32 @strcmp(i8*, i8*)

define i1 @_string_ne(i8* %0, i8* %1) {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #5
  %8 = icmp ne i32 %7, 0
  ret i1 %8
}

define i1 @_string_slt(i8* %0, i8* %1) {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #5
  %8 = icmp slt i32 %7, 0
  ret i1 %8
}

define i1 @_string_sle(i8* %0, i8* %1) {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #5
  %8 = icmp sle i32 %7, 0
  ret i1 %8
}

define i1 @_string_sgt(i8* %0, i8* %1) {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #5
  %8 = icmp sgt i32 %7, 0
  ret i1 %8
}

define i1 @_string_sge(i8* %0, i8* %1) {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #5
  %8 = icmp sge i32 %7, 0
  ret i1 %8
}