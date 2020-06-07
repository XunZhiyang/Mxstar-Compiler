; ModuleID = './src/Utils/builtin.c'
source_filename = "./src/Utils/builtin.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-unknown-linux-gnu"

@.str = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.2 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

; Function Attrs: nofree nounwind uwtable
define dso_local void @print(i8* %0) local_unnamed_addr #0 {
  %2 = tail call i32 (i8*, ...) @printf(i8* nonnull dereferenceable(1) getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i8* %0)
  ret void
}

; Function Attrs: nofree nounwind
declare dso_local i32 @printf(i8* nocapture readonly, ...) local_unnamed_addr #1

; Function Attrs: nofree nounwind uwtable
define dso_local void @println(i8* nocapture readonly %0) local_unnamed_addr #0 {
  %2 = tail call i32 @puts(i8* nonnull dereferenceable(1) %0)
  ret void
}

; Function Attrs: nofree nounwind uwtable
define dso_local void @printInt(i32 %0) local_unnamed_addr #0 {
  %2 = tail call i32 (i8*, ...) @printf(i8* nonnull dereferenceable(1) getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32 %0)
  ret void
}

; Function Attrs: nofree nounwind uwtable
define dso_local void @printlnInt(i32 %0) local_unnamed_addr #0 {
  %2 = tail call i32 (i8*, ...) @printf(i8* nonnull dereferenceable(1) getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3, i64 0, i64 0), i32 %0)
  ret void
}

; Function Attrs: nofree nounwind uwtable
define dso_local i8* @getString() local_unnamed_addr #0 {
  %1 = tail call noalias dereferenceable_or_null(256) i8* @malloc(i64 256) #9
  %2 = tail call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i8* %1)
  ret i8* %1
}

; Function Attrs: argmemonly nounwind willreturn
declare void @llvm.lifetime.start.p0i8(i64 immarg, i8* nocapture) #2

; Function Attrs: nofree nounwind
declare dso_local noalias i8* @malloc(i64) local_unnamed_addr #1

; Function Attrs: nofree nounwind
declare dso_local i32 @__isoc99_scanf(i8* nocapture readonly, ...) local_unnamed_addr #1

; Function Attrs: argmemonly nounwind willreturn
declare void @llvm.lifetime.end.p0i8(i64 immarg, i8* nocapture) #2

; Function Attrs: nounwind uwtable
define dso_local i32 @getInt() local_unnamed_addr #3 {
  %1 = alloca i32, align 4
  %2 = bitcast i32* %1 to i8*
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %2) #9
  %3 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32* nonnull %1)
  %4 = load i32, i32* %1, align 4, !tbaa !2
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %2) #9
  ret i32 %4
}

; Function Attrs: nofree nounwind uwtable
define dso_local noalias i8* @toString(i32 %0) local_unnamed_addr #0 {
  %2 = icmp eq i32 %0, 0
  br i1 %2, label %10, label %3

3:                                                ; preds = %1, %3
  %4 = phi i32 [ %6, %3 ], [ %0, %1 ]
  %5 = phi i32 [ %7, %3 ], [ 0, %1 ]
  %6 = sdiv i32 %4, 10
  %7 = add nuw nsw i32 %5, 1
  %8 = add i32 %4, 9
  %9 = icmp ult i32 %8, 19
  br i1 %9, label %10, label %3

10:                                               ; preds = %3, %1
  %11 = phi i32 [ 1, %1 ], [ %7, %3 ]
  %12 = icmp slt i32 %0, 0
  %13 = lshr i32 %0, 31
  %14 = add i32 %11, %13
  %15 = add nuw nsw i32 %14, 1
  %16 = zext i32 %15 to i64
  %17 = tail call noalias i8* @malloc(i64 %16) #9
  %18 = zext i32 %14 to i64
  %19 = getelementptr inbounds i8, i8* %17, i64 %18
  store i8 0, i8* %19, align 1, !tbaa !6
  br i1 %12, label %20, label %22

20:                                               ; preds = %10
  %21 = sub nsw i32 0, %0
  store i8 45, i8* %17, align 1, !tbaa !6
  br label %22

22:                                               ; preds = %20, %10
  %23 = phi i32 [ %21, %20 ], [ %0, %10 ]
  %24 = phi i32 [ 1, %20 ], [ 0, %10 ]
  %25 = icmp sgt i32 %14, %24
  br i1 %25, label %26, label %61

26:                                               ; preds = %22
  %27 = sext i32 %14 to i64
  %28 = zext i32 %24 to i64
  %29 = sub nsw i64 %27, %28
  %30 = xor i64 %28, -1
  %31 = and i64 %29, 1
  %32 = icmp eq i64 %31, 0
  br i1 %32, label %40, label %33

33:                                               ; preds = %26
  %34 = add nsw i64 %27, -1
  %35 = srem i32 %23, 10
  %36 = trunc i32 %35 to i8
  %37 = add nsw i8 %36, 48
  %38 = getelementptr inbounds i8, i8* %17, i64 %34
  store i8 %37, i8* %38, align 1, !tbaa !6
  %39 = sdiv i32 %23, 10
  br label %40

40:                                               ; preds = %26, %33
  %41 = phi i64 [ %27, %26 ], [ %34, %33 ]
  %42 = phi i32 [ %23, %26 ], [ %39, %33 ]
  %43 = sub nsw i64 0, %27
  %44 = icmp eq i64 %30, %43
  br i1 %44, label %61, label %45

45:                                               ; preds = %40, %45
  %46 = phi i64 [ %54, %45 ], [ %41, %40 ]
  %47 = phi i32 [ %59, %45 ], [ %42, %40 ]
  %48 = add nsw i64 %46, -1
  %49 = srem i32 %47, 10
  %50 = trunc i32 %49 to i8
  %51 = add nsw i8 %50, 48
  %52 = getelementptr inbounds i8, i8* %17, i64 %48
  store i8 %51, i8* %52, align 1, !tbaa !6
  %53 = sdiv i32 %47, 10
  %54 = add nsw i64 %46, -2
  %55 = srem i32 %53, 10
  %56 = trunc i32 %55 to i8
  %57 = add nsw i8 %56, 48
  %58 = getelementptr inbounds i8, i8* %17, i64 %54
  store i8 %57, i8* %58, align 1, !tbaa !6
  %59 = sdiv i32 %47, 100
  %60 = icmp sgt i64 %54, %28
  br i1 %60, label %45, label %61

61:                                               ; preds = %40, %45, %22
  ret i8* %17
}

; Function Attrs: nounwind readonly uwtable
define dso_local i32 @string__length(i8* nocapture readonly %0) local_unnamed_addr #4 {
  %2 = tail call i64 @strlen(i8* nonnull dereferenceable(1) %0) #10
  %3 = trunc i64 %2 to i32
  ret i32 %3
}

; Function Attrs: argmemonly nofree nounwind readonly
declare dso_local i64 @strlen(i8* nocapture) local_unnamed_addr #5

; Function Attrs: nofree nounwind uwtable
define dso_local noalias i8* @string__substring(i8* nocapture readonly %0, i32 %1, i32 %2) local_unnamed_addr #0 {
  %4 = sub nsw i32 %2, %1
  %5 = add nsw i32 %4, 1
  %6 = sext i32 %5 to i64
  %7 = tail call noalias i8* @malloc(i64 %6) #9
  %8 = icmp sgt i32 %2, %1
  br i1 %8, label %9, label %16

9:                                                ; preds = %3
  %10 = sext i32 %1 to i64
  %11 = getelementptr i8, i8* %0, i64 %10
  %12 = xor i32 %1, -1
  %13 = add i32 %12, %2
  %14 = zext i32 %13 to i64
  %15 = add nuw nsw i64 %14, 1
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* nonnull align 1 dereferenceable(1) %7, i8* nonnull align 1 dereferenceable(1) %11, i64 %15, i1 false)
  br label %16

16:                                               ; preds = %9, %3
  %17 = sext i32 %4 to i64
  %18 = getelementptr inbounds i8, i8* %7, i64 %17
  store i8 0, i8* %18, align 1, !tbaa !6
  ret i8* %7
}

; Function Attrs: norecurse nounwind readonly uwtable
define dso_local i32 @string__parseInt(i8* nocapture readonly %0) local_unnamed_addr #6 {
  %2 = load i8, i8* %0, align 1, !tbaa !6
  %3 = icmp eq i8 %2, 45
  %4 = zext i1 %3 to i64
  %5 = getelementptr inbounds i8, i8* %0, i64 %4
  %6 = load i8, i8* %5, align 1, !tbaa !6
  %7 = add i8 %6, -48
  %8 = icmp ult i8 %7, 10
  br i1 %8, label %9, label %22

9:                                                ; preds = %1, %9
  %10 = phi i64 [ %17, %9 ], [ %4, %1 ]
  %11 = phi i8 [ %19, %9 ], [ %6, %1 ]
  %12 = phi i32 [ %16, %9 ], [ 0, %1 ]
  %13 = zext i8 %11 to i32
  %14 = mul nsw i32 %12, 10
  %15 = add i32 %14, -48
  %16 = add i32 %15, %13
  %17 = add nuw i64 %10, 1
  %18 = getelementptr inbounds i8, i8* %0, i64 %17
  %19 = load i8, i8* %18, align 1, !tbaa !6
  %20 = add i8 %19, -48
  %21 = icmp ult i8 %20, 10
  br i1 %21, label %9, label %22

22:                                               ; preds = %9, %1
  %23 = phi i32 [ 0, %1 ], [ %16, %9 ]
  %24 = sub i32 0, %23
  %25 = select i1 %3, i32 %24, i32 %23
  ret i32 %25
}

; Function Attrs: norecurse nounwind readonly uwtable
define dso_local i32 @string__ord(i8* nocapture readonly %0, i32 %1) local_unnamed_addr #6 {
  %3 = sext i32 %1 to i64
  %4 = getelementptr inbounds i8, i8* %0, i64 %3
  %5 = load i8, i8* %4, align 1, !tbaa !6
  %6 = sext i8 %5 to i32
  ret i32 %6
}

; Function Attrs: nofree nounwind uwtable
define dso_local noalias i8* @_string_concatenate(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #0 {
  %3 = tail call i64 @strlen(i8* nonnull dereferenceable(1) %0) #10
  %4 = trunc i64 %3 to i32
  %5 = tail call i64 @strlen(i8* nonnull dereferenceable(1) %1) #10
  %6 = trunc i64 %5 to i32
  %7 = add nsw i32 %6, %4
  %8 = add nsw i32 %7, 1
  %9 = sext i32 %8 to i64
  %10 = tail call noalias i8* @malloc(i64 %9) #9
  %11 = icmp sgt i32 %4, 0
  br i1 %11, label %12, label %14

12:                                               ; preds = %2
  %13 = and i64 %3, 4294967295
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* align 1 %10, i8* align 1 %0, i64 %13, i1 false)
  br label %14

14:                                               ; preds = %12, %2
  %15 = icmp sgt i32 %6, 0
  br i1 %15, label %16, label %21

16:                                               ; preds = %14
  %17 = shl i64 %3, 32
  %18 = ashr exact i64 %17, 32
  %19 = and i64 %5, 4294967295
  %20 = getelementptr i8, i8* %10, i64 %18
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* align 1 %20, i8* align 1 %1, i64 %19, i1 false)
  br label %21

21:                                               ; preds = %16, %14
  %22 = sext i32 %7 to i64
  %23 = getelementptr inbounds i8, i8* %10, i64 %22
  store i8 0, i8* %23, align 1, !tbaa !6
  ret i8* %10
}

; Function Attrs: nounwind readonly uwtable
define dso_local signext i8 @_string_eq(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #4 {
  %3 = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %0, i8* nonnull dereferenceable(1) %1) #10
  %4 = icmp eq i32 %3, 0
  %5 = zext i1 %4 to i8
  ret i8 %5
}

; Function Attrs: nofree nounwind readonly
declare dso_local i32 @strcmp(i8* nocapture, i8* nocapture) local_unnamed_addr #7

; Function Attrs: nounwind readonly uwtable
define dso_local signext i8 @_string_ne(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #4 {
  %3 = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %0, i8* nonnull dereferenceable(1) %1) #10
  %4 = icmp ne i32 %3, 0
  %5 = zext i1 %4 to i8
  ret i8 %5
}

; Function Attrs: nounwind readonly uwtable
define dso_local signext i8 @_string_slt(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #4 {
  %3 = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %0, i8* nonnull dereferenceable(1) %1) #10
  %4 = lshr i32 %3, 31
  %5 = trunc i32 %4 to i8
  ret i8 %5
}

; Function Attrs: nounwind readonly uwtable
define dso_local signext i8 @_string_sle(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #4 {
  %3 = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %0, i8* nonnull dereferenceable(1) %1) #10
  %4 = icmp slt i32 %3, 1
  %5 = zext i1 %4 to i8
  ret i8 %5
}

; Function Attrs: nounwind readonly uwtable
define dso_local signext i8 @_string_sgt(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #4 {
  %3 = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %0, i8* nonnull dereferenceable(1) %1) #10
  %4 = icmp sgt i32 %3, 0
  %5 = zext i1 %4 to i8
  ret i8 %5
}

; Function Attrs: nounwind readonly uwtable
define dso_local signext i8 @_string_sge(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #4 {
  %3 = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %0, i8* nonnull dereferenceable(1) %1) #10
  %4 = lshr i32 %3, 31
  %5 = trunc i32 %4 to i8
  %6 = xor i8 %5, 1
  ret i8 %6
}

; Function Attrs: nofree nounwind
declare i32 @puts(i8* nocapture readonly) local_unnamed_addr #8

; Function Attrs: argmemonly nounwind willreturn
declare void @llvm.memcpy.p0i8.p0i8.i64(i8* noalias nocapture writeonly, i8* noalias nocapture readonly, i64, i1 immarg) #2

attributes #0 = { nofree nounwind uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="true" "no-jump-tables"="false" "no-nans-fp-math"="true" "no-signed-zeros-fp-math"="true" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="true" "use-soft-float"="false" }
attributes #1 = { nofree nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "no-infs-fp-math"="true" "no-nans-fp-math"="true" "no-signed-zeros-fp-math"="true" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="true" "use-soft-float"="false" }
attributes #2 = { argmemonly nounwind willreturn }
attributes #3 = { nounwind uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="true" "no-jump-tables"="false" "no-nans-fp-math"="true" "no-signed-zeros-fp-math"="true" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="true" "use-soft-float"="false" }
attributes #4 = { nounwind readonly uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="true" "no-jump-tables"="false" "no-nans-fp-math"="true" "no-signed-zeros-fp-math"="true" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="true" "use-soft-float"="false" }
attributes #5 = { argmemonly nofree nounwind readonly "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "no-infs-fp-math"="true" "no-nans-fp-math"="true" "no-signed-zeros-fp-math"="true" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="true" "use-soft-float"="false" }
attributes #6 = { norecurse nounwind readonly uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="true" "no-jump-tables"="false" "no-nans-fp-math"="true" "no-signed-zeros-fp-math"="true" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="true" "use-soft-float"="false" }
attributes #7 = { nofree nounwind readonly "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "no-infs-fp-math"="true" "no-nans-fp-math"="true" "no-signed-zeros-fp-math"="true" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="true" "use-soft-float"="false" }
attributes #8 = { nofree nounwind }
attributes #9 = { nounwind }
attributes #10 = { nounwind readonly }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 10.0.0 "}
!2 = !{!3, !3, i64 0}
!3 = !{!"int", !4, i64 0}
!4 = !{!"omnipotent char", !5, i64 0}
!5 = !{!"Simple C/C++ TBAA"}
!6 = !{!4, !4, i64 0}
