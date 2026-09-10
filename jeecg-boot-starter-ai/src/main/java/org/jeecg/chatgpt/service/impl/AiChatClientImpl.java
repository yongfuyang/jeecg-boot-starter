package org.jeecg.chatgpt.service.impl;

import dev.langchain4j.exception.AuthenticationException;
import org.jeecg.chatgpt.dto.chat.MultiChatMessage;
import org.jeecg.chatgpt.service.AiChatService;
import org.jeecg.common.ai.IAiChatClient;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description: AI聊天门面桥接实现，业务模块通过IAiChatClient软依赖AI能力，避免直接引用starter-ai类型
 * @Author: zhang
 * @Date: 2026-09-10
 */
public class AiChatClientImpl implements IAiChatClient {

	private final AiChatService aiChatService;

	public AiChatClientImpl(AiChatService aiChatService) {
		this.aiChatService = aiChatService;
	}

	@Override
	public String multiCompletions(String systemPrompt, String... userPrompts) {
		List<MultiChatMessage> messages = new LinkedList<>();
		messages.add(MultiChatMessage.builder().role(MultiChatMessage.Role.SYSTEM).content(systemPrompt).build());
		for (String userPrompt : userPrompts) {
			messages.add(MultiChatMessage.builder().role(MultiChatMessage.Role.USER).content(userPrompt).build());
		}
		try {
			return aiChatService.multiCompletions(messages);
		} catch (AuthenticationException e) {
			throw new RuntimeException("生成失败：令牌无效或已过期");
		}
	}
}
