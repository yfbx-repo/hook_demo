# hook_demo

VirtualXposed 初始化时需要翻墙

## 操作步骤
1. 安装 [VirtualXposed](https://github.com/android-hacker/VirtualXposed/blob/vxp/CHINESE.md),注意0.18.2版本之前只支持32位App
2. 安装目标APP,安装hook程序
3. 进入VirtualXposed，在主屏长按添加应用，将目标APP和hook程序添加到虚拟机
4. 在虚拟机中打开`Xposed Installer`,进入模块，勾选hook模块，重启Xposed
5. 在虚拟机中打开hook程序，切换到后台,再进入`Xposed Installer`日志模块清除日志
6. 操作目标APP，进入`Xposed Installer`日志模块刷新日志

    
    
## hook修改
  修改hook程序代码，筛选要hook的进程包名和方法
