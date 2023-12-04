package com.darkanddarker.auction.common.config;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CustomPageableResolver implements HandlerMethodArgumentResolver {

    private final PageableHandlerMethodArgumentResolver delegate;

    public CustomPageableResolver(SortHandlerMethodArgumentResolver sortResolver) {
        this.delegate = new PageableHandlerMethodArgumentResolver(sortResolver);
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return delegate.supportsParameter(methodParameter);
    }

    @Override
    public Pageable resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        Pageable originalPageable = delegate.resolveArgument(methodParameter, mavContainer, webRequest, binderFactory);

        return PageRequest.of(
                originalPageable.getPageNumber(),
                originalPageable.getPageSize(),
                originalPageable.getSort().descending()); // Set default sorting to DESC

    }
}
