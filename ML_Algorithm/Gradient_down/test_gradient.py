'''
Created on 2018年9月27日

@author: qiguangqin
'''
import numpy as np

import matplotlib.pyplot as plt


    
def dJ(theta): ##求导函数
    
    return 2*(theta-2.5)

def J(theta):  ##求出损失函数
    
    return (theta-2.5)**2-1

def gradient_descent(initial_theta,eta,epsilon=1e-8):
    
    theta=initial_theta

    theta_history=[]
    
    theta_history.append(initial_theta)
    
    while True:
        
        gradient=dJ(theta)
        
        last_theta=theta
        
        theta=theta-eta*gradient
        
        theta_history.append(theta)
        
        if(abs(J(theta)-J(last_theta))<epsilon):

            break
        
    return theta,J(theta),theta_history


def plot_theta_history(theta_history):
    
    plot_x=np.linspace(-1,6,141)
    
    plot_y=(plot_x-2.5)**2-1
    
    plt.plot(plot_x,plot_y,alpha=0.5)
    
    plt.scatter(np.array(theta_history),J(np.array(theta_history)),color="r",alpha=0.7)
    
    plt.show()

def main():
  

    theta,J_theta,theta_history=gradient_descent(0.0,0.1)
    
    plot_theta_history(theta_history)
    
    print(theta,J_theta)
    
if __name__ == '__main__':
    main()