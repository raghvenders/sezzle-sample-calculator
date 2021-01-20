/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.raghv.demo.sezzle;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;


public class Item {
	private long key;
	private String text;

	private Item() {}

	public Item(long key, String text) {
		this.key = key;
		this.text = text;
	}

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Item item = (Item) o;
		return key == item.key &&
				Objects.equals(text, item.text);
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, text);
	}

	@Override
	public String toString() {
		return "Item{" +
				"key=" + key +
				", text='" + text + '\'' +
				'}';
	}
}
// end::code[]