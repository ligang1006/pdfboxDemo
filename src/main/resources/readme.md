#pdfbox指南
这里只列了大纲，后续会继续补充

*  创建PDF文档
*  添加页面
*  加载文件
*  删除文件
*  文档属性
*  添加文档
*  添加多行文档
*  读取文档
* 插入图片
* 加密PDF文档
* 分割PDF文档
* 





****

9、插入图片

分别使用PDImageXObject类的createFromFile()以及PDPageContentStream类的drawImage()方法将图像插入到PDF文档中。    

PDFBox库中的类PDImageXObject表示图像。 它提供了执行与图像相关的操作所需的所有方法，例如插入图像，设置图像高度，设置图像宽度等。

****


10、加密

使用StandardProtectionPolicy和AccessPermission类提供的方法加密PDF文档。
AccessPermission类用于通过为其分配访问权限来保护PDF文档。 使用此教程，您可以限制用户执行以下操作。

打印文档修改文档的内容复制或提取文档的内容添加或修改注释填写交互式表单域提取文字和图形以便视障人士使用汇编文件打印质量下降
****

11、拆分PDF

可以使用Splitter类将给定的PDF文档分割为多个PDF文档。 该类用于将给定的PDF文档分成几个其他文档。

****








原文链接：https://www.yiibai.com/pdfbox/pdfbox_encrypting_a_pdf_document.html







