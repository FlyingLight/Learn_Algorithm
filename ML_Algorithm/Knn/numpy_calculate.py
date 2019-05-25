# -*- coding: UTF-8 -*-
'''
Created on 2018��7��18��


@author: qiguangqin
'''

import numpy as np


X=np.arange(1,13,1).reshape(4,3)

x1=np.arange(1,5,1)

np.log2(X) 

2**X ## 2 幂次数 为矩阵的值

'''
矩阵之间的运算 A+B A-B  

A B
'''

aa=np.array([[1,3],[2,4]])

bb=np.all(aa>2,axis=1)

print("bb=",bb)

b=np.empty(shape=2)

b= aa[:,0]+aa[:,1]

print(aa[:,0],aa[:,1],b,"adfa")

Y=np.ones(shape=(4,3))

xy=X*Y

print(xy) ##A *B 表示矩阵 A B 逐个元素相乘！  A/B  表示对应元素相除的结果

XY=X.dot(Y.reshape(3,4)) ##　表示　X 点乘 Y

#xy=x1.dot(Y.reshape(3,4)) 

xy=Y.reshape(3,4).dot(x1)

X.T ## 表示X的转至操作

print(XY,XY[:,-1])

Z=X[:3,:3].copy() ## 修改子矩阵的值，不影响原来的矩阵值

Z[2,2]=98

print(X,Z) 

print(np.sum(X))

aa=np.ones(shape=10)

print(aa,"qgq")

'''
矩阵和向量之间的运算 
'''
print(X**2)
Vect=np.arange(2,8,2)

print(X+Vect)  ##　4*3的 XY 与 1*3 Vect 向量相加 , 列相同，向量与每一行做加法

Vect.dot(X.T)

print(Vect*X)