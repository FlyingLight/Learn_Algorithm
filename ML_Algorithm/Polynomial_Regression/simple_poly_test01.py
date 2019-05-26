'''
Created on 2018年11月15日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

from sklearn.linear_model import LinearRegression

def get_data():
    
    np.random.seed(666)
    
    x=np.random.uniform(-3,3,size=100)
    
    X=x.reshape(-1,1)
    
    y=0.5*x**2+x+2+np.random.normal(0,1,size=100)
    
    return X,y

def get_poly_regression():
    
    X,y=get_data()
    
    X2=np.hstack([X,X**2])  
    
    print(X2.shape)
    
    '''
    X2 包含 x 和 x^2 两个特征
    '''
    
    lin_reg=LinearRegression()
    
    lin_reg.fit(X2,y)
    
    y_predict=lin_reg.predict(X2)
    
    print(lin_reg.coef_,lin_reg.intercept_)
    
    plt.scatter(X,y,alpha=0.5)
    
    plt.plot(np.sort(X[:,0]),y_predict[np.argsort(X[:,0])],color='r',alpha=0.5) ## plot(array,array)
    
    '''
        需要把X[:,0]进行排序操作，否则绘制图形，就是乱的
    '''
    
    plt.show()
    
def main():
    
    get_data()
    
    get_poly_regression()

if __name__ == '__main__':
    
    main()