package org.jeecg.common.ai;

/**
 * @Description: AI聊天门面，业务模块通过本接口软依赖AI能力，避免直接引用jeecg-boot-starter-ai的类型
 * 运行时classpath中无AI依赖时，注入方需使用@Autowired(required=false)并判空
 * @Author: zhang
 * @Date: 2026-09-10
 */
public interface IAiChatClient {

	/**
	 * 多角色问答（system + 多条user消息）
	 *
	 * @param systemPrompt 系统提示词
	 * @param userPrompts 用户提示词（按顺序依次追加）
	 * @return AI回答内容
	 * @author zhang
	 * @date 2026/9/10
	 */
	String multiCompletions(String systemPrompt, String... userPrompts);
}
