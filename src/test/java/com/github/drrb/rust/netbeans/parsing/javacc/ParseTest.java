package com.github.drrb.rust.netbeans.parsing.javacc;

import com.github.drrb.rust.netbeans.test.junit412.Parameterized;
import com.github.drrb.rust.netbeans.test.junit412.Parameterized.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@RunWith(Parameterized.class)
public class ParseTest {
    private static final List<TestSrc> EXCLUDED_SOURCES = Stream.of(
            "blocks/blocks.rs",
            "if_then_else/if_statement.rs",
            "macro_complex.rs"
    )
            .map(Paths::get)
            .map(TestFile::get)
            .map(TestSrc::get)
            .collect(toList());

    @Parameters(name = "{0}")
    public static Iterable<TestSrc> sources() {
        List<TestSrc> allSources = TestSrc.all().collect(toList());
        allSources.removeAll(EXCLUDED_SOURCES);
        return allSources.stream().collect(toList());
    }

    private final TestSrc sourceFile;

    public ParseTest(TestSrc sourceFile) {
        this.sourceFile = sourceFile;
    }

    @Test
    public void testParse() throws Exception {
        SimpleNode output = RustParser.parse(sourceFile.getPath());

        dump(output, "");
    }


    public void dump(SimpleNode node, String prefix) {
        System.out.println(prefix + RustParserTreeConstants.jjtNodeName[node.id] + "[" + node.value + "]");
        if (node.children != null) {
            for (int i = 0; i < node.children.length; ++i) {
                SimpleNode n = (SimpleNode) node.children[i];
                if (n != null) {
                    dump(n, prefix + " ");
                }
            }
        }
    }

}