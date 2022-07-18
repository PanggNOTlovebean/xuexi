### Git

### 对象模型

树-文件-提交

文件有hash 存的是snapshot

树=树+文件

提交都包含一棵树

```
// 文件就是一组数据
type blob = array<byte>

// 一个包含文件和目录的目录
type tree = map<string, tree | blob>

// 每个提交都包含一个父辈，元数据和顶层树
type commit = struct {
    parent: array<commit>
    author: string
    message: string
    snapshot: tree
}
```

HEAD指向当前分支，如果当前指向的是某个提交的哈希值而不是引用，称为分离head

rebase  a b命令用来改变 b 的父亲就成了a 减少分支数

git rebase -i 交互式调整记录

cherrypick a b c 使 a b c提交成为当前分支的孩子

git clone 会默认绑定origin/main main

push前强制pull 保持和远程仓库一致 
