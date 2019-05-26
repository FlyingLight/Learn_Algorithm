'''
Created on 2019年3月12日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

from sklearn.datasets import  make_moons ## produce simulated data

from sklearn.preprocessing import PolynomialFeatures

from sklearn.pipeline import  Pipeline

from sklearn.svm import LinearSVC,SVC

from sklearn.preprocessing import StandardScaler

from matplotlib.colors import ListedColormap



def get_data():
     
    X,y=make_moons(noise=0.15,random_state=666)
    
    return X,y

def PolynomialSVC(degree,C=1e8):
    
    return Pipeline([("poly",PolynomialFeatures(degree=degree)),
        
        ("stand",StandardScaler()),\
        
        ("lin_svm",LinearSVC(C=C))
        
        ])

def kernel_svc(degree,C=1e8):
    
    return Pipeline([
        
        ("stand",StandardScaler()),\
        ("kernel_svc",SVC(kernel="poly",degree=degree,C=C)) ## 直接使用 poly 作为核，多项式svc
        ])


def rbf_kernel_svc(C,gamma=1.0):
    
    return Pipeline([
        
        ("stand",StandardScaler()),\
        
        ("kernel_rbf_svc",SVC(kernel="rbf",gamma=gamma,C=C))
        ])

def plot_decision_boundary(model,axis):  ##　绘制决策边界
    
    x0,x1=np.meshgrid(np.linspace(axis[0],axis[1],int((axis[1]-axis[0])*100)).reshape(-1,1),\
                      
                      np.linspace(axis[2],axis[3],int((axis[3]-axis[2])*100)).reshape(-1,1),\
                    ) 
    
    X_new=np.c_[x0.ravel(),x1.ravel()] ## 按照行进行合并矩阵，连个矩阵左右相加
    
    y_predict=model.predict(X_new) 
    
    zz=y_predict.reshape(x0.shape)
    
    custom_cmap = ListedColormap(['#D3D3D3','#FFF59D','#B0C4DE'])
    
    plt.contourf(x0, x1, zz, cmap=custom_cmap) 
    
    
def main():
    
    X,y=get_data()
    
    degree=3
    
    poly_svc=PolynomialSVC(degree)
    
    poly_kernel_svc=kernel_svc(degree)
    
    C=1.0
    
    rbf_svc=rbf_kernel_svc(C)
    
    poly_svc.fit(X, y)
    
    poly_kernel_svc.fit(X,y)
    
    rbf_svc.fit(X,y)
    
    
    
    plot_decision_boundary(poly_svc, axis=[-1.5,2.5,-1.0,1.5])
    
    #print(X.shape,y.shape)
    
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.5)
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.5)
    
    plt.show()
    
    
    plot_decision_boundary(poly_kernel_svc, axis=[-1.5,2.5,-1.0,1.5])
    
    #print(X.shape,y.shape)
    
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.5)
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.5)
    
    plt.show()
    
    
    plot_decision_boundary(rbf_svc, axis=[-1.5,2.5,-1.0,1.5])
    
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.5)
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.5)
    
    plt.show()

if __name__ == '__main__':
    main()