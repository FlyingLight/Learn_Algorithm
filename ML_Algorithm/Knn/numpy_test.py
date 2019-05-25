# -*- coding: UTF-8 -*-
import numpy as np


'''
Created on 2018年7月3日

@author: qiguangqin
'''
'''
arr=array.array('i',[i for i in range(10)])

print(arr[:])  ##


g=[i for i in range(1,10,2)]

g[2]="jeeld"

print(g[:]*2)  # 打印两遍

切片 
nparr[::2] 

nparr[1:6] 左开右闭区间 
nparr[5:]  到结尾

X[:2] 取得矩阵的前2个元素，也就是前2行
X[:2][:3]  去这个X[:2]的前3列，不满足取前两行
'''
nparr=np.array([i for i in range(1,11,1)]) ##使用np 建立 向量和矩阵
print(nparr)

##nparr[1] nparr[2] 修改这个值

#nparr[2]=89
print(nparr.dtype)

nparr2=np.array([1.0,2,3,5.8])

print(nparr2.dtype,nparr[::-2])

print(nparr[::-2])

nparr1=np.arange(1,12,2).reshape(2,3)

print(nparr1[:2],nparr1[1,1])# 访问矩阵前两行 

print(nparr1[:2,:2]) #截取出前 2*2 的矩阵 切片，必须使用，运算符

print(nparr1[:2,::2])  #前两行 ，列的间隔为2
'''
nparr1[1,:] 取第二行元素,当成矩阵处理
nparr1[1]   取第二行元素,当成数组处理,取一列元素，就不可以了
'''
print(nparr1[1,:].ndim)

print(nparr1[:,1]) ## 取出第二列元素

x1,x2,x3=np.split((np.arange(2,20,2)),[3,8])  ##将一个向量分隔切片 [:3] [3:8] [8:]  切成这样的三段

print(x1,x2,x3)

x4,x5=np.split((np.arange(3,27,3)),[5]) ## 将[:5] [5:] 分成两段

z=np.ones(shape=3, dtype=int)

zz=np.vstack([nparr1,z])

zz1=np.hstack([z.reshape(3,-1),zz])

zz2=np.concatenate([nparr1,z.reshape(1,-1)])  

z33=np.concatenate([zz,z.reshape(3,-1)],axis=1)  ##横向合并

print(zz,zz1[:,-1],zz2,z33)

A=np.arange(16).reshape(4,4)

A1,A2=np.split(A,[2])  ## 将矩阵A[:2,:] 作为一个子矩阵，将A[2:,:] 作为另一个子矩阵

print(A1,A2)

A11,A22=np.split(A,[2],axis=1) ##  左右进行分隔 

A3,A4=np.split(A,[2],axis=1)   #左右  A[:,:2] A[:,2:]

upper,lower=np.vsplit(A,[2]) ##垂直分隔矩阵，分成两个矩阵，A[:2,:] A[2:,:]

left,right=np.hsplit(A,[2])  ##水平分隔矩阵，分成两个矩阵，左右  A[:,:2] A[:,2:]

print(left,right)

'''
需要把矩阵，样本值和最后一列的label值分开
'''

sample,label=np.hsplit(A, [-1]) ## 水平分成一个矩阵，和一个列向量  A[:,-1] ,可以用于数据的处理，抽出 样本 和label

print("sample=",sample)
      
print("label=",label)

label[:,0]

tt=np.array([[1,3,4,5],[2,3,4,5]])

ad=(tt).dot(5)  

print("dada",ad)