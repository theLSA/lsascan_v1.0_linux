# lsascan_v1.0_linux

概述：

前段时间用java写了个综合扫描器lsascan_v1.0，最近整理了下代码，正式开源，大牛绕过...,由于时间和技术问题，此版本存在一些bug,但不影响正常使用，本人会持续更新此扫描器，github地址：https://github.com/theLSA/lsascan_v1.0_linux
欢迎pr。


环境：

linux+java1.8

ps:如需在windows下使用，要细微修改一些代码



功能简述：

1.ip扫描：分为单ip扫描，自定义ip段扫描，c段扫描

2.端口扫描：自定义端口范围扫描，端口集合扫描

3.嗅探：调用nmap

4.列本机进程，执行系统命令

5.爆破：调用hydra

6.显示本机信息



结语：

一个简单的软件，原本是在windows下开发的，后来迁移到linux下，如果要在windows下使用的话也就修改一下两个调用外部程序的程序路径即可,大家有好的建议欢迎联系我。

联系方式：

邮箱：

lsasguge196@gmail.com

2894400469@qq.com

QQ:2894600469


此版本有一些已知bug：

1.扫描ip/端口的时候界面会卡死，直到扫描结束才行,估计是线程池的问题。
2.原本在windows下可以get到局域网的mac,迁移到linux后getmac还不太完善。


# 欢迎访问我的博客：http://www.lsablog.com




