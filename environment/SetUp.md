# Memos for Automatical Environment Set Up

## Chocolatey

### cheat sheet

```
choco list -localonly	# show installed packages
choco --timeout	0		# set the timeout length to infinite (default 2700s)
choco install PKG		# install PKG
```

## Anaconda3

### Installation

```
chocolatey install anaconda3 --params '"/JustMe"'
```

### conda command cheat sheet

```
conda info -e							# 環境の確認
conda create -n <name>					# 環境の作成
conda create -n <name1> --clone	<name2>	# name2をコピーしていname1を作成
conda env remove -n <name>				# 環境の削除
conda activate <name>					# 環境の有効化
conda deactivate						# 環境の無効化
```

### Set Up of Virtual Environments

以下で機械学習用の環境を構築

```
conda add channel conda config --append channels free pyde prometeia
conda create -n py35 python=3.5
conda install pandas=0.23.4 matplotlib=3.0.0 numpy=1.13.3 scipy=0.19.1 ipython=5.1.0 scikit-learn=0.18.1
conda install -c conda-forge jupyter_contrib_nbextension
```

base環境にenvironment_kernelsをインストールし、jupyter notebookを起動して上記環境のkernelを動かすとコードの実行ができた。(py35環境だとエラー有り)