	.text
	.file	"builtin.c"
	.globl	print                   # -- Begin function print
	.p2align	2
	.type	print,@function
print:                                  # @print
	.cfi_startproc
# %bb.0:
	lui	a1, %hi(.L.str)
	addi	a1, a1, %lo(.L.str)
	mv	a2, a0
	mv	a0, a1
	mv	a1, a2
	tail	printf
.Lfunc_end0:
	.size	print, .Lfunc_end0-print
	.cfi_endproc
                                        # -- End function
	.globl	println                 # -- Begin function println
	.p2align	2
	.type	println,@function
println:                                # @println
	.cfi_startproc
# %bb.0:
	tail	puts
.Lfunc_end1:
	.size	println, .Lfunc_end1-println
	.cfi_endproc
                                        # -- End function
	.globl	printInt                # -- Begin function printInt
	.p2align	2
	.type	printInt,@function
printInt:                               # @printInt
	.cfi_startproc
# %bb.0:
	lui	a1, %hi(.L.str.2)
	addi	a1, a1, %lo(.L.str.2)
	mv	a2, a0
	mv	a0, a1
	mv	a1, a2
	tail	printf
.Lfunc_end2:
	.size	printInt, .Lfunc_end2-printInt
	.cfi_endproc
                                        # -- End function
	.globl	printlnInt              # -- Begin function printlnInt
	.p2align	2
	.type	printlnInt,@function
printlnInt:                             # @printlnInt
	.cfi_startproc
# %bb.0:
	lui	a1, %hi(.L.str.3)
	addi	a1, a1, %lo(.L.str.3)
	mv	a2, a0
	mv	a0, a1
	mv	a1, a2
	tail	printf
.Lfunc_end3:
	.size	printlnInt, .Lfunc_end3-printlnInt
	.cfi_endproc
                                        # -- End function
	.globl	getString               # -- Begin function getString
	.p2align	2
	.type	getString,@function
getString:                              # @getString
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	a0, zero, 256
	mv	a1, zero
	call	malloc
	mv	s0, a0
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	mv	a1, s0
	call	__isoc99_scanf
	mv	a0, s0
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end4:
	.size	getString, .Lfunc_end4-getString
	.cfi_endproc
                                        # -- End function
	.globl	getInt                  # -- Begin function getInt
	.p2align	2
	.type	getInt,@function
getInt:                                 # @getInt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	addi	a1, sp, 8
	call	__isoc99_scanf
	lw	a0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end5:
	.size	getInt, .Lfunc_end5-getInt
	.cfi_endproc
                                        # -- End function
	.globl	toString                # -- Begin function toString
	.p2align	2
	.type	toString,@function
toString:                               # @toString
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	sw	s1, 4(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	.cfi_offset s1, -12
	mv	s0, a0
	beqz	a0, .LBB6_3
# %bb.1:                                # %.preheader1
	mv	a0, zero
	lui	a1, 419430
	addi	a1, a1, 1639
	addi	a2, zero, 18
	mv	a3, s0
.LBB6_2:                                # =>This Inner Loop Header: Depth=1
	mulh	a4, a3, a1
	srli	a5, a4, 31
	srai	a4, a4, 2
	addi	s1, a3, 9
	add	a3, a4, a5
	addi	a0, a0, 1
	bltu	a2, s1, .LBB6_2
	j	.LBB6_4
.LBB6_3:
	addi	a0, zero, 1
.LBB6_4:
	srli	a1, s0, 31
	add	s1, a0, a1
	addi	a0, s1, 1
	mv	a1, zero
	call	malloc
	add	a1, a0, s1
	addi	a2, zero, -1
	sb	zero, 0(a1)
	bge	a2, s0, .LBB6_6
# %bb.5:
	mv	a7, zero
	blt	a7, s1, .LBB6_7
	j	.LBB6_15
.LBB6_6:
	neg	s0, s0
	addi	a1, zero, 45
	sb	a1, 0(a0)
	addi	a7, zero, 1
	bge	a7, s1, .LBB6_15
.LBB6_7:
	srai	a5, s1, 31
	sub	a1, s1, a7
	not	t0, a7
	andi	a1, a1, 1
	addi	a6, zero, -1
	bnez	a1, .LBB6_9
# %bb.8:
	mv	a3, s1
	mv	a2, a5
	j	.LBB6_10
.LBB6_9:
	srai	a1, s1, 31
	addi	a3, s1, -1
	sltu	a2, a3, s1
	add	a1, a1, a2
	addi	a2, a1, -1
	lui	a1, 419430
	addi	a1, a1, 1639
	mulh	a1, s0, a1
	srli	a4, a1, 31
	srai	a1, a1, 2
	add	a1, a1, a4
	addi	a4, zero, 10
	mul	a4, a1, a4
	sub	a4, s0, a4
	addi	a4, a4, 48
	add	s0, a0, a3
	sb	a4, 0(s0)
	mv	s0, a1
.LBB6_10:
	snez	a1, s1
	add	a1, a5, a1
	neg	a1, a1
	neg	a4, s1
	xor	a4, t0, a4
	xor	a1, a6, a1
	or	a1, a4, a1
	beqz	a1, .LBB6_15
# %bb.11:                               # %.preheader
	lui	a1, 419430
	addi	a4, a1, 1639
	addi	a6, zero, 10
	lui	a1, 335544
	addi	t0, a1, 1311
	j	.LBB6_13
.LBB6_12:                               #   in Loop: Header=BB6_13 Depth=1
	sgtz	a5, a2
	add	s0, a1, s0
	beqz	a5, .LBB6_15
.LBB6_13:                               # =>This Inner Loop Header: Depth=1
	mv	a1, a3
	mulh	a3, s0, a4
	srli	a5, a3, 31
	srai	a3, a3, 2
	add	a5, a3, a5
	mul	a3, a5, a6
	sub	a3, s0, a3
	addi	a3, a3, 48
	add	s1, a1, a0
	sb	a3, -1(s1)
	addi	a3, a1, -2
	sltu	a1, a3, a1
	add	a1, a2, a1
	addi	a2, a1, -1
	mulh	a1, a5, a4
	srli	s1, a1, 31
	srli	a1, a1, 2
	add	a1, a1, s1
	mul	a1, a1, a6
	sub	a1, a5, a1
	addi	a1, a1, 48
	add	a5, a0, a3
	sb	a1, 0(a5)
	mulh	a1, s0, t0
	srli	s0, a1, 31
	srai	a1, a1, 5
	bnez	a2, .LBB6_12
# %bb.14:                               #   in Loop: Header=BB6_13 Depth=1
	sltu	a5, a7, a3
	add	s0, a1, s0
	bnez	a5, .LBB6_13
.LBB6_15:
	lw	s1, 4(sp)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end6:
	.size	toString, .Lfunc_end6-toString
	.cfi_endproc
                                        # -- End function
	.globl	string__length          # -- Begin function string__length
	.p2align	2
	.type	string__length,@function
string__length:                         # @string__length
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	call	strlen
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end7:
	.size	string__length, .Lfunc_end7-string__length
	.cfi_endproc
                                        # -- End function
	.globl	string__substring       # -- Begin function string__substring
	.p2align	2
	.type	string__substring,@function
string__substring:                      # @string__substring
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	sw	s1, 20(sp)
	sw	s2, 16(sp)
	sw	s3, 12(sp)
	sw	s4, 8(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	.cfi_offset s1, -12
	.cfi_offset s2, -16
	.cfi_offset s3, -20
	.cfi_offset s4, -24
	mv	s4, a2
	mv	s0, a1
	mv	s3, a0
	sub	s2, a2, a1
	addi	a0, s2, 1
	srai	a1, a0, 31
	call	malloc
	mv	s1, a0
	bge	s0, s4, .LBB8_2
# %bb.1:
	add	a1, s3, s0
	mv	a0, s1
	mv	a2, s2
	call	memcpy
.LBB8_2:
	add	a0, s1, s2
	sb	zero, 0(a0)
	mv	a0, s1
	lw	s4, 8(sp)
	lw	s3, 12(sp)
	lw	s2, 16(sp)
	lw	s1, 20(sp)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end8:
	.size	string__substring, .Lfunc_end8-string__substring
	.cfi_endproc
                                        # -- End function
	.globl	string__parseInt        # -- Begin function string__parseInt
	.p2align	2
	.type	string__parseInt,@function
string__parseInt:                       # @string__parseInt
	.cfi_startproc
# %bb.0:
	lbu	a6, 0(a0)
	xori	a1, a6, 45
	seqz	t0, a1
	add	a1, a0, t0
	lbu	a4, 0(a1)
	addi	a1, a4, -48
	andi	a1, a1, 255
	addi	a2, zero, 9
	bltu	a2, a1, .LBB9_6
# %bb.1:                                # %.preheader
	mv	a5, zero
	mv	a1, zero
	addi	a7, zero, 10
.LBB9_2:                                # =>This Inner Loop Header: Depth=1
	andi	a2, a4, 255
	mul	a1, a1, a7
	addi	a3, t0, 1
	add	a4, a0, a3
	lbu	a4, 0(a4)
	add	a1, a1, a2
	addi	a1, a1, -48
	sltu	t0, a3, t0
	addi	a2, a4, -48
	andi	a2, a2, 255
	add	a5, a5, t0
	mv	t0, a3
	bltu	a2, a7, .LBB9_2
# %bb.3:
	addi	a0, zero, 45
	bne	a6, a0, .LBB9_5
.LBB9_4:
	neg	a1, a1
.LBB9_5:
	mv	a0, a1
	ret
.LBB9_6:
	mv	a1, zero
	addi	a0, zero, 45
	beq	a6, a0, .LBB9_4
	j	.LBB9_5
.Lfunc_end9:
	.size	string__parseInt, .Lfunc_end9-string__parseInt
	.cfi_endproc
                                        # -- End function
	.globl	string__ord             # -- Begin function string__ord
	.p2align	2
	.type	string__ord,@function
string__ord:                            # @string__ord
	.cfi_startproc
# %bb.0:
	add	a0, a0, a1
	lb	a0, 0(a0)
	ret
.Lfunc_end10:
	.size	string__ord, .Lfunc_end10-string__ord
	.cfi_endproc
                                        # -- End function
	.globl	_string_concatenate     # -- Begin function _string_concatenate
	.p2align	2
	.type	_string_concatenate,@function
_string_concatenate:                    # @_string_concatenate
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -48
	.cfi_def_cfa_offset 48
	sw	ra, 44(sp)
	sw	s0, 40(sp)
	sw	s1, 36(sp)
	sw	s2, 32(sp)
	sw	s3, 28(sp)
	sw	s4, 24(sp)
	sw	s5, 20(sp)
	sw	s6, 16(sp)
	sw	s7, 12(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	.cfi_offset s1, -12
	.cfi_offset s2, -16
	.cfi_offset s3, -20
	.cfi_offset s4, -24
	.cfi_offset s5, -28
	.cfi_offset s6, -32
	.cfi_offset s7, -36
	mv	s2, a1
	mv	s3, a0
	addi	s5, zero, -1
	call	strlen
	mv	s1, a0
	mv	a0, s2
	call	strlen
	mv	s4, a0
	add	s6, a0, s1
	addi	a0, s6, 1
	srai	a1, a0, 31
	call	malloc
	addi	s7, zero, 1
	mv	s0, a0
	blt	s1, s7, .LBB11_2
# %bb.1:
	and	a2, s1, s5
	mv	a0, s0
	mv	a1, s3
	call	memcpy
.LBB11_2:
	blt	s4, s7, .LBB11_4
# %bb.3:
	and	a2, s4, s5
	add	a0, s0, s1
	mv	a1, s2
	call	memcpy
.LBB11_4:
	add	a0, s0, s6
	sb	zero, 0(a0)
	mv	a0, s0
	lw	s7, 12(sp)
	lw	s6, 16(sp)
	lw	s5, 20(sp)
	lw	s4, 24(sp)
	lw	s3, 28(sp)
	lw	s2, 32(sp)
	lw	s1, 36(sp)
	lw	s0, 40(sp)
	lw	ra, 44(sp)
	addi	sp, sp, 48
	ret
.Lfunc_end11:
	.size	_string_concatenate, .Lfunc_end11-_string_concatenate
	.cfi_endproc
                                        # -- End function
	.globl	_string_eq              # -- Begin function _string_eq
	.p2align	2
	.type	_string_eq,@function
_string_eq:                             # @_string_eq
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	call	strcmp
	seqz	a0, a0
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end12:
	.size	_string_eq, .Lfunc_end12-_string_eq
	.cfi_endproc
                                        # -- End function
	.globl	_string_ne              # -- Begin function _string_ne
	.p2align	2
	.type	_string_ne,@function
_string_ne:                             # @_string_ne
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	call	strcmp
	snez	a0, a0
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end13:
	.size	_string_ne, .Lfunc_end13-_string_ne
	.cfi_endproc
                                        # -- End function
	.globl	_string_slt             # -- Begin function _string_slt
	.p2align	2
	.type	_string_slt,@function
_string_slt:                            # @_string_slt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	call	strcmp
	srli	a0, a0, 31
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end14:
	.size	_string_slt, .Lfunc_end14-_string_slt
	.cfi_endproc
                                        # -- End function
	.globl	_string_sle             # -- Begin function _string_sle
	.p2align	2
	.type	_string_sle,@function
_string_sle:                            # @_string_sle
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	call	strcmp
	slti	a0, a0, 1
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end15:
	.size	_string_sle, .Lfunc_end15-_string_sle
	.cfi_endproc
                                        # -- End function
	.globl	_string_sgt             # -- Begin function _string_sgt
	.p2align	2
	.type	_string_sgt,@function
_string_sgt:                            # @_string_sgt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	call	strcmp
	sgtz	a0, a0
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end16:
	.size	_string_sgt, .Lfunc_end16-_string_sgt
	.cfi_endproc
                                        # -- End function
	.globl	_string_sge             # -- Begin function _string_sge
	.p2align	2
	.type	_string_sge,@function
_string_sge:                            # @_string_sge
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	call	strcmp
	not	a0, a0
	srli	a0, a0, 31
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end17:
	.size	_string_sge, .Lfunc_end17-_string_sge
	.cfi_endproc
                                        # -- End function
	.type	.L.str,@object          # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"%s"
	.size	.L.str, 3

	.type	.L.str.2,@object        # @.str.2
.L.str.2:
	.asciz	"%d"
	.size	.L.str.2, 3

	.type	.L.str.3,@object        # @.str.3
.L.str.3:
	.asciz	"%d\n"
	.size	.L.str.3, 4

	.ident	"clang version 10.0.0 "
	.section	".note.GNU-stack","",@progbits
