package com.agnor99.solver;

import java.util.List;

public interface ITypeBatchParser<T> {
    T parse(List<String> toParse);
}
