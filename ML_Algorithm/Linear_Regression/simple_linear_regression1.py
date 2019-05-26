#coding=utf-8
'''
Created on 2018��9��13��

@author: qiguangqin
'''


from sklearn import datasets as dt

from sklearn.model_selection import train_test_split

from linear_regression.simple_lineaer_regression_01 import simple_linear_regression02

import matplotlib.pyplot as plt

from test03.metrics import get_mse, get_rmse, get_mae,r2_score


'''
波士顿房产数据 

一维数据，简单线性回归，取RM 数据 6
'''

def get_data():
    
    boston=dt.load_boston()  ##一共有506个样本，每一个样本有13个特征，简单线性回单，RM room的多少来预测

    x=boston.data[:,5]

    y=boston.target

    x=x[y<50.0]  ##使用fancy indexing ，把最大值给去掉,形成真正的数据集

    y=y[y<50.0]

    return x,y

def get_train_test(x,y):
    
    x_train,x_test,y_train,y_test=train_test_split(x,y,test_size=0.2,random_state=666)
    
    return x_train,x_test,y_train,y_test

def main():
    
    x,y=get_data()
    
    x_train,x_test,y_train,y_test=get_train_test(x, y)
    
    reg= simple_linear_regression02()
    
    reg.fit(x_train, y_train)
    
    y_predict=reg.predict(x_test)
    
    score=reg.score(x_test, y_test)
    
    #mse=np.sum((y_test-y_predict)**2)/(y_test.shape[0])
    
    mse=get_mse(y_test,y_predict)
    
    #rmse=sqrt(mse)
    
    rmse=get_rmse(y_test,y_predict)
    
    #mae=np.sum(np.absolute(y_test-y_predict))/len(y_test)
    
    mae=get_mae(y_test,y_predict)
    
    r2_score1=r2_score(y_test, y_predict)
    
    print("mse=",mse,"rmse=",rmse,"mae=",mae,"r2_score=",r2_score1,"score=",score)
    
    plt.plot(x_test,reg.a_*x_test+reg.b_,alpha=0.7)
    
    plt.scatter(x_test,y_test,color='g',alpha=0.7)
    
    plt.show()


if __name__ == '__main__':
    
    main()