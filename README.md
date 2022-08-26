# hook_demo

Xposed框架HookDemo.   
该项目中的例子主要用来检测隐私合规相关问题，可自行添加需要hook的方法


## 操作步骤
1. 安装 [VirtualXposed](https://github.com/android-hacker/VirtualXposed/blob/vxp/CHINESE.md),注意0.18.2版本之前只支持32位App
2. 安装目标APP,安装hook程序
3. 进入VirtualXposed，在主屏长按添加应用，将目标APP和hook程序添加到虚拟机
4. 在虚拟机中打开`Xposed Installer`,进入模块，勾选hook模块，重启Xposed
5. 在虚拟机中打开并操作目标APP，在logcat中筛选Xposed查看日志

    
    
## Other
  - 修改hook程序代码，筛选要hook的进程包名和方法
  - VirtualXposed 初始化时需要翻墙
