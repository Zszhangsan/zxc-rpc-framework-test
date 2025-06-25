package zxc.rpc.zxcrpcremote.reflection.proxy.manul;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JavaFileCompiler {
    // 固定的输出目录路径
//    private static final String FIXED_OUTPUT_DIR = "./target/classes/";
    private static final String FIXED_OUTPUT_DIR = "zxc-rpc-remote/target/classes/";

    public static void complie(File javaFile) {
        JavaCompiler systemJavaCompiler = ToolProvider.getSystemJavaCompiler();
        // 获取文件管理器
        try (StandardJavaFileManager standardFileManager = systemJavaCompiler.getStandardFileManager(null, null, null)) {

            // 获取要编译的文件对象
            Iterable<? extends JavaFileObject> javaFileObjectsFromFiles =
                    standardFileManager.getJavaFileObjectsFromFiles(Arrays.asList(javaFile));

            // 设置编译选项（可选，例如指定输出目录）
            List<String> options = Arrays.asList("-d", FIXED_OUTPUT_DIR);
            // 创建编译任务

            JavaCompiler.CompilationTask task = systemJavaCompiler.getTask(
                    null,
                    standardFileManager,
                    null,
                    options,
                    null,
                    javaFileObjectsFromFiles
            );

            Boolean call = task.call();
            if (call) {
                System.out.println("编译成功");
            } else {
                System.out.println("编译失败");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 安全编译单个Java文件到固定输出目录
     * @param javaFile 要编译的Java文件
     * @return 编译是否成功
     */
    public static boolean compile1(File javaFile) {
        // 验证输入文件
        if (!validateInputFile(javaFile)) {
            return false;
        }

        // 准备输出目录
        File outputDir = prepareOutputDirectory();
        if (outputDir == null) {
            return false;
        }

        // 使用临时目录进行编译
        File tempDir = createTempDirectory();
        if (tempDir == null) {
            return false;
        }

        try {
            // 执行编译到临时目录
            boolean compileSuccess = compileToDirectory(javaFile, tempDir);
            if (!compileSuccess) {
                return false;
            }

            // 将编译结果从临时目录移动到目标目录
            return moveClassFiles(tempDir, outputDir, javaFile.getName());
        } finally {
            // 确保删除临时目录
            deleteDirectory(tempDir);
        }
    }

    private static boolean validateInputFile(File javaFile) {
        if (!javaFile.exists() || !javaFile.isFile() || !javaFile.getName().endsWith(".java")) {
            System.err.println("无效的Java文件: " + javaFile.getPath());
            return false;
        }
        return true;
    }

    private static File prepareOutputDirectory() {
        File outputDir = new File(FIXED_OUTPUT_DIR);
        if (!outputDir.exists() && !outputDir.mkdirs()) {
            System.err.println("无法创建输出目录: " + FIXED_OUTPUT_DIR);
            return null;
        }
        return outputDir;
    }

    private static File createTempDirectory() {
        try {
            File tempDir = Files.createTempDirectory("java-compile-").toFile();
            tempDir.deleteOnExit();
            return tempDir;
        } catch (IOException e) {
            System.err.println("无法创建临时目录: " + e.getMessage());
            return null;
        }
    }

    private static boolean compileToDirectory(File javaFile, File outputDir) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            System.err.println("JDK编译器不可用。请确保使用JDK而不是JRE运行此程序。");
            return false;
        }

        try (StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null)) {
            fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(outputDir));

            Iterable<? extends JavaFileObject> compilationUnits =
                    fileManager.getJavaFileObjectsFromFiles(Collections.singletonList(javaFile));

            return compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
        } catch (IOException e) {
            System.err.println("编译过程中发生错误: " + e.getMessage());
            return false;
        }
    }

    private static boolean moveClassFiles(File sourceDir, File targetDir, String javaFileName) {
        String baseName = javaFileName.substring(0, javaFileName.lastIndexOf('.'));

        try {
            // 查找所有生成的.class文件
            File[] classFiles = sourceDir.listFiles((dir, name) ->
                    name.startsWith(baseName) && name.endsWith(".class"));

            if (classFiles == null || classFiles.length == 0) {
                System.err.println("未生成任何.class文件");
                return false;
            }

            // 移动每个.class文件
            for (File classFile : classFiles) {
                Path targetPath = new File(targetDir, classFile.getName()).toPath();
                Files.move(classFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            }

            return true;
        } catch (IOException e) {
            System.err.println("移动.class文件时出错: " + e.getMessage());
            return false;
        }
    }

    private static void deleteDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        directory.delete();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("用法: SafeFileCompiler <Java文件路径>");
            System.out.println("示例:");
            System.out.println("  SafeFileCompiler HelloWorld.java");
            System.out.println("注意: 编译后的字节码将安全地输出到 " + FIXED_OUTPUT_DIR + " 目录");
            return;
        }

        File javaFile = new File(args[0]);
        boolean success = compile1(javaFile);

        System.exit(success ? 0 : 1);
    }
}