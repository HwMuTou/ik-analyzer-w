package org.wltea.analyzer.cfg;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Configuration 默认实现
 */
public class DefaultConfig implements Configuration {


    //分词器默认字典路径
    private static final String PATH_DIC_MAIN = "dic/main2012.dic";
    private static final String PATH_DIC_QUANTIFIER = "dic/quantifier.dic";

    //分词器配置文件路径
    private static final String FILE_NAME = "IKAnalyzer.cfg.xml";
    //配置属性——扩展字典
    private static final String EXT_DICT = "ext_dict";
    //配置属性——扩展停止词典
    private static final String EXT_STOP = "ext_stopwords";

    private Properties props;

    //是否使用smart方式分词
    private boolean useSmart;

    /**
     * 返回单例
     *
     * @return Configuration单例
     */
    public static Configuration getInstance() {
        return new DefaultConfig();
    }

    /**
     * 初始化配置文件
     */
    private DefaultConfig() {
        props = new Properties();

        InputStream input = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME);
        if (input != null) {
            try {
                props.loadFromXML(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 返回useSmart标志位
     * useSmart =true ，分词器使用智能切分策略， =false则使用细粒度切分
     *
     * @return useSmart
     */
    public boolean useSmart() {
        return useSmart;
    }

    /**
     * 设置useSmart标志位
     * useSmart =true ，分词器使用智能切分策略， =false则使用细粒度切分
     *
     * @param useSmart boolean
     */
    public void setUseSmart(boolean useSmart) {
        this.useSmart = useSmart;
    }

    /**
     * 获取主词典路径
     *
     * @return String 主词典路径
     */
    public String getMainDictionary() {
        return PATH_DIC_MAIN;
    }

    /**
     * 获取量词词典路径
     *
     * @return String 量词词典路径
     */
    public String getQuantifierDicionary() {
        return PATH_DIC_QUANTIFIER;
    }

    /**
     * 获取扩展字典配置路径
     *
     * @return List<String> 相对类加载器的路径
     */
    public List<String> getExtDictionarys() {
        String extDictCfg = props.getProperty(EXT_DICT);
        return getConfigs(extDictCfg);
    }

    /**
     * 获取扩展停止词典配置路径
     *
     * @return List<String> 相对类加载器的路径
     */
    public List<String> getExtStopWordDictionarys() {
        String extStopWordDictCfg = props.getProperty(EXT_STOP);
        return getConfigs(extStopWordDictCfg);
    }

    /**
     * String to Strings by ";"
     */
    private List<String> getConfigs(String configStr) {
        if (configStr == null) {
            return new ArrayList<>();
        }

        List<String> configs = new ArrayList<>();
        for (String config : configStr.split(";")) {
            if (config != null && !config.trim().isEmpty()) {
                configs.add(config.trim());
            }
        }
        return configs;
    }
}
