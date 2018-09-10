package org.wltea.analyzer.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

/**
 * IK分词器，Lucene Analyzer接口实现
 * 兼容Lucene 4.0版本
 */
public final class IKAnalyzer extends Analyzer {

    private boolean useSmart;

    public boolean useSmart() {
        return useSmart;
    }

    /**
     * IK分词器Lucene  Analyzer接口实现类
     *
     * 默认细粒度切分算法
     */
    public IKAnalyzer(){
        this(false);
    }

    /**
     * IK分词器Lucene Analyzer接口实现类
     *
     * @param useSmart 当为true时，分词器进行智能切分
     */
    public IKAnalyzer(boolean useSmart) {
        super();
        this.useSmart = useSmart;
    }

    /**
     * 重载Analyzer接口，构造分词组件
     */
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer _IKTokenizer = new IKTokenizer(this.useSmart());
        return new TokenStreamComponents(_IKTokenizer);
    }

}
