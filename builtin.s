	.text
	.file	"builtin.c"
	.globl	print                   # -- Begin function print
	.p2align	2
	.type	print,@function
print:                                  # @print
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 16
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	lw	a1, -16(s0)
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	call	printf
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
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
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 16
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	lw	a1, -16(s0)
	lui	a0, %hi(.L.str.1)
	addi	a0, a0, %lo(.L.str.1)
	call	printf
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
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
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 16
	.cfi_def_cfa s0, 0
	sw	a0, -12(s0)
	lw	a1, -12(s0)
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	call	printf
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
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
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 16
	.cfi_def_cfa s0, 0
	sw	a0, -12(s0)
	lw	a1, -12(s0)
	lui	a0, %hi(.L.str.3)
	addi	a0, a0, %lo(.L.str.3)
	call	printf
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
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
	addi	s0, sp, 16
	.cfi_def_cfa s0, 0
	addi	a0, zero, 256
	mv	a1, zero
	call	malloc
	sw	a0, -16(s0)
	lw	a1, -16(s0)
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	call	__isoc99_scanf
	lw	a0, -16(s0)
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
	sw	s0, 8(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 16
	.cfi_def_cfa s0, 0
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	addi	a1, s0, -12
	call	__isoc99_scanf
	lw	a0, -12(s0)
	lw	s0, 8(sp)
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
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 32
	.cfi_def_cfa s0, 0
	sw	a0, -12(s0)
	sw	zero, -16(s0)
	lw	a0, -12(s0)
	seqz	a0, a0
	lw	a1, -16(s0)
	add	a0, a1, a0
	sw	a0, -16(s0)
	lw	a0, -12(s0)
	sw	a0, -20(s0)
	j	.LBB6_1
.LBB6_1:                                # =>This Inner Loop Header: Depth=1
	lw	a0, -20(s0)
	beqz	a0, .LBB6_3
	j	.LBB6_2
.LBB6_2:                                #   in Loop: Header=BB6_1 Depth=1
	lw	a0, -20(s0)
	lui	a1, 419430
	addi	a1, a1, 1639
	mulh	a0, a0, a1
	srli	a1, a0, 31
	srai	a0, a0, 2
	add	a0, a0, a1
	sw	a0, -20(s0)
	lw	a0, -16(s0)
	addi	a0, a0, 1
	sw	a0, -16(s0)
	j	.LBB6_1
.LBB6_3:
	lw	a0, -12(s0)
	srli	a0, a0, 31
	lw	a1, -16(s0)
	add	a0, a1, a0
	sw	a0, -16(s0)
	lw	a0, -16(s0)
	addi	a0, a0, 1
	srai	a1, a0, 31
	call	malloc
	sw	a0, -24(s0)
	lw	a0, -24(s0)
	lw	a1, -16(s0)
	add	a0, a0, a1
	sb	zero, 0(a0)
	sw	zero, -28(s0)
	lw	a0, -12(s0)
	addi	a1, zero, -1
	blt	a1, a0, .LBB6_5
	j	.LBB6_4
.LBB6_4:
	lw	a0, -12(s0)
	neg	a0, a0
	sw	a0, -12(s0)
	lw	a0, -24(s0)
	lw	a1, -28(s0)
	addi	a2, a1, 1
	sw	a2, -28(s0)
	add	a0, a0, a1
	addi	a1, zero, 45
	sb	a1, 0(a0)
	j	.LBB6_5
.LBB6_5:
	lw	a0, -16(s0)
	addi	a0, a0, -1
	sw	a0, -32(s0)
	j	.LBB6_6
.LBB6_6:                                # =>This Inner Loop Header: Depth=1
	lw	a0, -32(s0)
	lw	a1, -28(s0)
	blt	a0, a1, .LBB6_9
	j	.LBB6_7
.LBB6_7:                                #   in Loop: Header=BB6_6 Depth=1
	lw	a0, -12(s0)
	lui	a1, 419430
	addi	a1, a1, 1639
	mulh	a2, a0, a1
	srli	a3, a2, 31
	srli	a2, a2, 2
	add	a2, a2, a3
	addi	a3, zero, 10
	mul	a2, a2, a3
	sub	a0, a0, a2
	addi	a0, a0, 48
	lw	a2, -24(s0)
	lw	a3, -32(s0)
	add	a2, a2, a3
	sb	a0, 0(a2)
	lw	a0, -12(s0)
	mulh	a0, a0, a1
	srli	a1, a0, 31
	srai	a0, a0, 2
	add	a0, a0, a1
	sw	a0, -12(s0)
	j	.LBB6_8
.LBB6_8:                                #   in Loop: Header=BB6_6 Depth=1
	lw	a0, -32(s0)
	addi	a0, a0, -1
	sw	a0, -32(s0)
	j	.LBB6_6
.LBB6_9:
	lw	a0, -24(s0)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
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
	sw	s0, 8(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 16
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	lw	a0, -16(s0)
	call	strlen
	lw	s0, 8(sp)
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
	addi	sp, sp, -48
	.cfi_def_cfa_offset 48
	sw	ra, 44(sp)
	sw	s0, 40(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 48
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	sw	a1, -20(s0)
	sw	a2, -24(s0)
	lw	a0, -24(s0)
	lw	a1, -20(s0)
	sub	a0, a0, a1
	addi	a0, a0, 1
	srai	a1, a0, 31
	call	malloc
	sw	a0, -32(s0)
	lw	a0, -20(s0)
	sw	a0, -36(s0)
	j	.LBB8_1
.LBB8_1:                                # =>This Inner Loop Header: Depth=1
	lw	a0, -36(s0)
	lw	a1, -24(s0)
	bge	a0, a1, .LBB8_4
	j	.LBB8_2
.LBB8_2:                                #   in Loop: Header=BB8_1 Depth=1
	lw	a0, -16(s0)
	lw	a1, -36(s0)
	add	a0, a0, a1
	lb	a0, 0(a0)
	lw	a2, -32(s0)
	lw	a3, -20(s0)
	sub	a1, a1, a3
	add	a1, a2, a1
	sb	a0, 0(a1)
	j	.LBB8_3
.LBB8_3:                                #   in Loop: Header=BB8_1 Depth=1
	lw	a0, -36(s0)
	addi	a0, a0, 1
	sw	a0, -36(s0)
	j	.LBB8_1
.LBB8_4:
	lw	a0, -32(s0)
	lw	a1, -24(s0)
	lw	a2, -20(s0)
	sub	a1, a1, a2
	add	a0, a0, a1
	sb	zero, 0(a0)
	lw	a0, -32(s0)
	lw	s0, 40(sp)
	lw	ra, 44(sp)
	addi	sp, sp, 48
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
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 32
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	addi	a0, zero, 1
	sw	a0, -20(s0)
	sw	zero, -24(s0)
	sw	zero, -28(s0)
	lw	a0, -16(s0)
	lb	a0, 0(a0)
	addi	a1, zero, 45
	bne	a0, a1, .LBB9_2
	j	.LBB9_1
.LBB9_1:
	addi	a0, zero, -1
	sw	a0, -20(s0)
	addi	a0, zero, 1
	sw	a0, -24(s0)
	j	.LBB9_2
.LBB9_2:
	j	.LBB9_3
.LBB9_3:                                # =>This Inner Loop Header: Depth=1
	lw	a0, -16(s0)
	lw	a1, -24(s0)
	add	a0, a0, a1
	lb	a1, 0(a0)
	addi	a2, zero, 48
	mv	a0, zero
	blt	a1, a2, .LBB9_5
	j	.LBB9_4
.LBB9_4:                                #   in Loop: Header=BB9_3 Depth=1
	lw	a0, -16(s0)
	lw	a1, -24(s0)
	add	a0, a0, a1
	lb	a0, 0(a0)
	slti	a0, a0, 58
	j	.LBB9_5
.LBB9_5:                                #   in Loop: Header=BB9_3 Depth=1
	andi	a0, a0, 1
	beqz	a0, .LBB9_8
	j	.LBB9_6
.LBB9_6:                                #   in Loop: Header=BB9_3 Depth=1
	lw	a0, -28(s0)
	addi	a1, zero, 10
	mul	a0, a0, a1
	lw	a1, -16(s0)
	lw	a2, -24(s0)
	add	a1, a1, a2
	lb	a1, 0(a1)
	add	a0, a0, a1
	addi	a0, a0, -48
	sw	a0, -28(s0)
	j	.LBB9_7
.LBB9_7:                                #   in Loop: Header=BB9_3 Depth=1
	lw	a0, -24(s0)
	addi	a0, a0, 1
	sw	a0, -24(s0)
	j	.LBB9_3
.LBB9_8:
	lw	a0, -20(s0)
	lw	a1, -28(s0)
	mul	a0, a0, a1
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
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
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 32
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	sw	a1, -20(s0)
	lw	a0, -16(s0)
	lw	a1, -20(s0)
	add	a0, a0, a1
	lb	a0, 0(a0)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
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
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 48
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	sw	a1, -24(s0)
	lw	a0, -16(s0)
	call	strlen
	sw	a0, -28(s0)
	lw	a0, -24(s0)
	call	strlen
	sw	a0, -32(s0)
	lw	a0, -28(s0)
	lw	a1, -32(s0)
	add	a0, a0, a1
	addi	a0, a0, 1
	srai	a1, a0, 31
	call	malloc
	sw	a0, -40(s0)
	sw	zero, -44(s0)
	j	.LBB11_1
.LBB11_1:                               # =>This Inner Loop Header: Depth=1
	lw	a0, -44(s0)
	lw	a1, -28(s0)
	bge	a0, a1, .LBB11_4
	j	.LBB11_2
.LBB11_2:                               #   in Loop: Header=BB11_1 Depth=1
	lw	a0, -16(s0)
	lw	a1, -44(s0)
	add	a0, a0, a1
	lb	a0, 0(a0)
	lw	a2, -40(s0)
	add	a1, a2, a1
	sb	a0, 0(a1)
	j	.LBB11_3
.LBB11_3:                               #   in Loop: Header=BB11_1 Depth=1
	lw	a0, -44(s0)
	addi	a0, a0, 1
	sw	a0, -44(s0)
	j	.LBB11_1
.LBB11_4:
	sw	zero, -48(s0)
	j	.LBB11_5
.LBB11_5:                               # =>This Inner Loop Header: Depth=1
	lw	a0, -48(s0)
	lw	a1, -32(s0)
	bge	a0, a1, .LBB11_8
	j	.LBB11_6
.LBB11_6:                               #   in Loop: Header=BB11_5 Depth=1
	lw	a0, -24(s0)
	lw	a1, -48(s0)
	add	a0, a0, a1
	lb	a0, 0(a0)
	lw	a2, -40(s0)
	lw	a3, -28(s0)
	add	a1, a3, a1
	add	a1, a2, a1
	sb	a0, 0(a1)
	j	.LBB11_7
.LBB11_7:                               #   in Loop: Header=BB11_5 Depth=1
	lw	a0, -48(s0)
	addi	a0, a0, 1
	sw	a0, -48(s0)
	j	.LBB11_5
.LBB11_8:
	lw	a0, -40(s0)
	lw	a1, -28(s0)
	lw	a2, -32(s0)
	add	a1, a1, a2
	add	a0, a0, a1
	sb	zero, 0(a0)
	lw	a0, -40(s0)
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
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 32
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	sw	a1, -24(s0)
	lw	a0, -16(s0)
	lw	a1, -24(s0)
	call	strcmp
	seqz	a0, a0
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
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
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 32
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	sw	a1, -24(s0)
	lw	a0, -16(s0)
	lw	a1, -24(s0)
	call	strcmp
	snez	a0, a0
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
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
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 32
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	sw	a1, -24(s0)
	lw	a0, -16(s0)
	lw	a1, -24(s0)
	call	strcmp
	srli	a0, a0, 31
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
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
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 32
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	sw	a1, -24(s0)
	lw	a0, -16(s0)
	lw	a1, -24(s0)
	call	strcmp
	slti	a0, a0, 1
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
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
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 32
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	sw	a1, -24(s0)
	lw	a0, -16(s0)
	lw	a1, -24(s0)
	call	strcmp
	sgtz	a0, a0
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
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
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 32
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	sw	a1, -24(s0)
	lw	a0, -16(s0)
	lw	a1, -24(s0)
	call	strcmp
	not	a0, a0
	srli	a0, a0, 31
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
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

	.type	.L.str.1,@object        # @.str.1
.L.str.1:
	.asciz	"%s\n"
	.size	.L.str.1, 4

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
