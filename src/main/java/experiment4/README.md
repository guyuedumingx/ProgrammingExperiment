### 关于util.graphutil(图可视化工具包)的使用说明

#### 相关文件

##### 接口

- [Graph](../util/graphutil/Graph.java) 图
- [GraphNode](../util/graphutil/GraphNode.java) 图中的节点
- [GraphEdge](../util/graphutil/GraphEdge.java) 图中的边
- [GraphRoute](../util/graphutil/GraphRoute.java) 图中的通路

##### 工具类

- [GraphUtil](../util/graphutil/GraphUtil.java) 用来将图导出到本地

##### 显示器

- [show.html](../util/graphutil/show.html) 用来将导出的图显示出来

##### 以上内容详细信息可参考[API文档](../util/graphutil/API文档.html)

#### 操作流程

1. 实现图相关的接口
2. 使用GraphUtil类中的exportGraph方法将图导出为后缀名为.ycx的文件
3. 使用谷歌浏览器（或其它主流的兼容性比较好的浏览器 ）打开show.html
4. 在页面中打开第2步中导出的.ycx文件，或者也可以直接拖拽进去
5. 成功导入文件后，点击页面中的解析按钮，即可将文件中图的信息解析渲染到页面中

<font size="3" color="red">**如果最后渲染时页面出现的报错，那绝对不是工具出了问题，请仔细检查你自己的图中给出的信息是否正确**</font>
