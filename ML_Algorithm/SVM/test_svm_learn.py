'''
Created on 2019年3月10日

@author: qiguangqin
'''
import numpy as np

from sklearn.preprocessing import StandardScaler

from sklearn.model_selection import train_test_split

from sklearn.svm import LinearSVC  ## 使用 SVM 进行分类

import matplotlib.pyplot as plt

from sklearn.datasets import load_iris

from matplotlib.colors import ListedColormap

from sklearn.metrics.classification import confusion_matrix

from sklearn.metrics import f1_score,precision_score,recall_score,roc_curve

def get_data():
    
    iris=load_iris()
    
    y=iris.target[iris.target<2]
    
    X=iris.data[iris.target<2,:2]
    
    return X,y


def plot_decision_boundary(model,axis):  ##　绘制决策边界
    
    x0,x1=np.meshgrid(np.linspace(axis[0],axis[1],int((axis[1]-axis[0])*100)).reshape(-1,1),\
                      
                      np.linspace(axis[2],axis[3],int((axis[3]-axis[2])*100)).reshape(-1,1),\
                    ) 
    
    X_new=np.c_[x0.ravel(),x1.ravel()] ## 按照行进行合并矩阵，连个矩阵左右相加
    
    y_predict=model.predict(X_new) 
    
    zz=y_predict.reshape(x0.shape)
    
    custom_cmap = ListedColormap(['#D3D3D3','#FFF59D','#B0C4DE'])
    
    plt.contourf(x0, x1, zz, cmap=custom_cmap) 
    
    w=model.coef_[0] #绘图 y=kx+b 只需要coef[0]
    
    b=model.intercept_[0] 
    
    '''
    w0*x0+w1*x1+b=0 ---> decision_boundary
    
    => x1= -w0/w1*x0-b/w1
    '''
    plot_x=np.linspace(axis[0],axis[1],200)
    
    '''
    w0*x0+w1*x1+b=1 ---> support vector
    
    => x1= -w0/w1*x0-b/w1+1/w1
    '''
    up_y=-w[0]/w[1]*plot_x-b/w[1]+1/w[1]
    
    '''
    w0*x0+w1*x1+b=-1 ---> support vector
    
    => x1= -w0/w1*x0-b/w1-1/w1
    '''
    
    down_y=-w[0]/w[1]*plot_x-b/w[1]-1/w[1]
    
    up_index=(up_y>=axis[2]) &(up_y<=axis[3])
    
    down_index=(down_y>=axis[2]) &(down_y<=axis[3])
    
    plt.plot(plot_x[up_index],up_y[up_index],color="g")
    
    plt.plot(plot_x[down_index],down_y[down_index],color="m")
    
def stand_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)
    
    stand=StandardScaler()
    
    stand.fit(X_train)
    
    X_train_std=stand.transform(X_train)
    
    stand1=StandardScaler()
    
    stand1.fit(X_test)
    
    X_test_std=stand.transform(X_test)
    
    return X_train_std,X_test_std,y_train,y_test
    
def main():
    
    X,y=get_data()
    
    X_train_std,X_test_std,y_train,y_test=stand_train_test(X, y)
    
    
    '''
    ---hard margin svm ---
    '''  
    
    svc=LinearSVC(C=1e9,multi_class='ovr') ## 几乎就是一个hard svm，如果C 很大
    
    svc.fit(X_train_std,y_train)
    
    y_predict=svc.predict(X_train_std)
    
    cm=confusion_matrix(y_train,y_predict)
      
    print("confusion_,matrix=",cm,"pre_score=",precision_score(y_train,y_predict),"recall_score=",recall_score(y_train,y_predict),"f1_score=",f1_score(y_train,y_predict))
    
    plot_decision_boundary(svc, axis=[-3,3,-3,3])
    
    plt.scatter(X_train_std[y_train==0,0],X_train_std[y_train==0,1])

    plt.scatter(X_train_std[y_train==1,0],X_train_std[y_train==1,1])
    
    plt.show()
  
    '''
    ---soft margin svm ---
    '''  
    svc1=LinearSVC(C=1e-1) ## 此时就是一个soft svm，有一个蓝色的outlier 被错误的分类
    
    svc1.fit(X_train_std,y_train)
    
    y_predict1=svc1.predict(X_train_std)
    
    cm=confusion_matrix(y_train,y_predict1)
    
    print("confusion_,matrix=",cm,"pre_score=",precision_score(y_train,y_predict1),"recall_score=",recall_score(y_train,y_predict1),"f1_score=",f1_score(y_train,y_predict1))
    
    plot_decision_boundary(svc1, axis=[-3,3,-3,3])
    
    plt.scatter(X_train_std[y_train==0,0],X_train_std[y_train==0,1])

    plt.scatter(X_train_std[y_train==1,0],X_train_std[y_train==1,1])
    
    plt.show()
    
if __name__ == '__main__':
    main()