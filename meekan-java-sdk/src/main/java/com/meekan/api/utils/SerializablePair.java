package com.meekan.api.utils;

import java.io.Serializable;

/**
 * Pair of two {@link Serializable} objects.
 * 
 * @author idog
 * 
 * @param <F>
 * @param <S>
 */
public class SerializablePair<F extends Serializable, S extends Serializable> implements Serializable {

	public final F first;
	public final S second;

	public SerializablePair(final F first, final S second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public String toString() {
		return "Pair [first=" + first + ", second=" + second + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SerializablePair))
			return false;
		SerializablePair other = (SerializablePair) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		return true;
	}
}
