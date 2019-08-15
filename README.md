# CrazyJavaExcercisesSolutions

有些解答是有BUG的，其中有些是我没发现，有些是懒得改了。

根据我做题的进度更新。希望我能做得快一点。

上传了一些class文件，但这只是因为我懒得一个个上传了。不保证class文件的正确性，请读者自己编译再运行。

# 编码问题
第一步：

源文件大多使用utf-8，但是也有部分gbk编码的。
建议将gbk编码的文件转为utf-8后再运行。

但是这样依然不能运行，因为中国Windows默认编码是GBK。

所以需要第二步，有两种方案：
## 解决方案1
在每个javac命令的末尾加上-Dfile.encoding=UTF-8；例如
```
javac demo.java -Dfile.encoding=UTF-8
```
## 解决方案2
设置环境变量JAVA_TOOL_OPTIONS 的值为 -Dfile.encoding=UTF-8
