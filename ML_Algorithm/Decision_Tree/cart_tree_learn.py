'''
Created on 2019年5月8日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

from sklearn.datasets import make_moons

from sklearn.tree import DecisionTreeClassifier

from matplotlib.colors import ListedColormap


def plot_decision_boundary(model,axis):  ##　绘制决策边界
    
    x0,x1=np.meshgrid(np.linspace(axis[0],axis[1],int((axis[1]-axis[0])*100)).reshape(-1,1),\
                      
                      np.linspace(axis[2],axis[3],int((axis[3]-axis[2])*100)).reshape(-1,1),\
                    ) 
    
    X_new=np.c_[x0.ravel(),x1.ravel()] ## 按照行进行合并矩阵，连个矩阵左右相加
    
    y_predict=model.predict(X_new) 
    
    zz=y_predict.reshape(x0.shape)
    
    custom_cmap = ListedColormap(['#D3D3D3','#FFF59D','#B0C4DE'])
    
    plt.contourf(x0, x1, zz, cmap=custom_cmap)  


def get_data():
    
    X,y=make_moons(noise=0.25,random_state=666)
    
    return X,y

def use_simple_dec_tree1(X,y):
    
    dt_clf=DecisionTreeClassifier()  ## 容易发生过拟合
    
    dt_clf.fit(X,y)
    
    plot_decision_boundary(dt_clf, axis=[-1.5,2.5,-1.0,1.5])
    
    #plt.show()
    
def use_simple_dec_tree2(X,y):
    
    dt_clf=DecisionTreeClassifier(max_depth=2)  ## 容易发生过拟合
    
    dt_clf.fit(X,y)
    
    plot_decision_boundary(dt_clf, axis=[-1.5,2.5,-1.0,1.5])
    
    #plt.show()
    
def use_simple_dec_tree3(X,y):
    
    dt_clf=DecisionTreeClassifier(min_samples_split=10)  ## 容易发生过拟合  
    
    dt_clf.fit(X,y)
    
    plot_decision_boundary(dt_clf, axis=[-1.5,2.5,-1.0,1.5])
    
    #plt.show()
    
def use_simple_dec_tree4(X,y):
    
    dt_clf=DecisionTreeClassifier(min_samples_leaf=6)  ## 容易发生过拟合  
    
    dt_clf.fit(X,y)
    
    plot_decision_boundary(dt_clf, axis=[-1.5,2.5,-1.0,1.5])
    
    #plt.show()
    
def use_simple_dec_tree5(X,y):
    
    dt_clf=DecisionTreeClassifier(max_leaf_nodes=4)  ## 容易发生过拟合  
    
    dt_clf.fit(X,y)
    
    plot_decision_boundary(dt_clf, axis=[-1.5,2.5,-1.0,1.5])
    
    dt_clf.fit(X,y)
    
    plot_decision_boundary(dt_clf, axis=[-1.5,2.5,-1.0,1.5])
    
    #plt.show()
    
def main():
    
    X,y=get_data()
    
    use_simple_dec_tree5(X, y)
    
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.5)
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.5)
    
    plt.show()

if __name__ == '__main__':
    main()