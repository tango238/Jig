# JIG-Gradle-Plugin

GradleプロジェクトでJIGドキュメントを出力するプラグインです。

## 導入方法

`build.gradle` を編集してください。

[プラグインリポジトリ](https://plugins.gradle.org/plugin/org.dddjava.jig-gradle-plugin) を参照してください。

Javaプロジェクトの場合、`compileJava`および`processResources`に依存させておくと便利です。

```
jigReports.dependsOn(compileJava, processResources)
```

## タスク

`gradle tasks` で出力される "JIG tasks" を参照してください。

## Getting Started

1. プラグインの追加（導入方法参照）
1. プロジェクトのビルドおよびJIGの実行

### プロジェクトのビルドおよびJIGの実行

```
$ gradle clean build jig
```

`build/jig` ディレクトリにJIGドキュメントが出力されます。

## 設定

以下の例ようにプロパティを指定してください。例の値はデフォルトです。
```
jig {
    modelPattern = '.+\\.domain\\.model\\..+'
    repositoryPattern = '.+Repository'
    documentTypes = ['ServiceMethodCallHierarchy','PackageDependency','ApplicationList','DomainList','BranchList','EnumUsage','BooleanService']
    outputDirectory = '' //出力ディレクトリ
    outputOmitPrefix= '.+\\.(service|domain\\.(model|basic))\\.' //出力時に省略する接頭辞パターン
}
```

## プラグイン開発者向け

### SNAPSHOTの適用

`mavenLocal()` へのインストールを行う

```
./gradlew build
```

`build.gradle` に以下を記述

```
buildscript {
    repositories {
        mavenLocal()
    }
    dependencies {
        classpath 'org.dddjava.jig:jig-gradle-plugin:+'
    }
}

apply plugin: 'java'
apply plugin: 'org.dddjava.jig-gradle-plugin'
```
