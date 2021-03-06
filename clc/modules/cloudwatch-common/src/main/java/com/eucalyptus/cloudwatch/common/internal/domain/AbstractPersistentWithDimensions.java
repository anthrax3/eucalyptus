/*************************************************************************
 * Copyright 2009-2013 Ent. Services Development Corporation LP
 *
 * Redistribution and use of this software in source and binary forms,
 * with or without modification, are permitted provided that the
 * following conditions are met:
 *
 *   Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 *   Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer
 *   in the documentation and/or other materials provided with the
 *   distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 ************************************************************************/
package com.eucalyptus.cloudwatch.common.internal.domain;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.apache.log4j.Logger;

import com.eucalyptus.entities.AbstractPersistent;

@MappedSuperclass
public class AbstractPersistentWithDimensions extends AbstractPersistent {

	public static final int MAX_DIM_NUM = 10;
	private static final Logger LOG = Logger.getLogger(AbstractPersistentWithDimensions.class);
	public AbstractPersistentWithDimensions() {
		super();
	}

	public Collection<DimensionEntity> getDimensions() {
		TreeSet<DimensionEntity> dimensions = new TreeSet<DimensionEntity>();
		for (int dimNum = 1; dimNum <= MAX_DIM_NUM; dimNum++) {
			String dimName = getDimName(dimNum);
			String dimValue = getDimValue(dimNum);
			if (dimName != null && dimValue != null) {
				dimensions.add(new DimensionEntity(dimName, dimValue));
			}
		}
		return dimensions;
	}
	
	public Map<String, String> getDimensionMap() {
    TreeMap<String, String> dimensionMap = new TreeMap<String, String>();
    for (DimensionEntity entity: getDimensions()) {
      dimensionMap.put(entity.getName(), entity.getValue());
    }
    return dimensionMap;
	}
	/**
	 * Sets all dimensions.  This method copies the input parameter, do not lazily load it.
	 * @param dimensions
	 */
	public void setDimensions(Collection<DimensionEntity> dimensions) {
		if (dimensions != null && dimensions.size() > MAX_DIM_NUM) {
			throw new IllegalArgumentException("Too many dimensions, " + dimensions.size());
		}
		for (int dimNum = 1; dimNum <= MAX_DIM_NUM; dimNum++) {
			setDimName(dimNum, null);
			setDimValue(dimNum, null);
		}
		if (dimensions == null) {
			return;
		}
		dimensions = new TreeSet<DimensionEntity>(dimensions); // sort it
		Iterator<DimensionEntity> iter = dimensions.iterator();
		DimensionEntity d = null;
		for (int dimNum = 1; dimNum <= MAX_DIM_NUM; dimNum++) {
			if (!iter.hasNext()) {
				return;
			}
			d = iter.next();
			setDimName(dimNum, d.getName());
			setDimValue(dimNum, d.getValue());
		}
	}



	@Column( name = "dim_1_name" )
	private String dim1Name;
	@Column( name = "dim_2_name" )
	private String dim2Name;
	@Column( name = "dim_3_name" )
	private String dim3Name;
	@Column( name = "dim_4_name" )
	private String dim4Name;
	@Column( name = "dim_5_name" )
	private String dim5Name;
	@Column( name = "dim_6_name" )
	private String dim6Name;
	@Column( name = "dim_7_name" )
	private String dim7Name;
	@Column( name = "dim_8_name" )
	private String dim8Name;
	@Column( name = "dim_9_name" )
	private String dim9Name;
	@Column( name = "dim_10_name" )
	private String dim10Name;

	@Column( name = "dim_1_value" )
	private String dim1Value;
	@Column( name = "dim_2_value" )
	private String dim2Value;
	@Column( name = "dim_3_value" )
	private String dim3Value;
	@Column( name = "dim_4_value" )
	private String dim4Value;
	@Column( name = "dim_5_value" )
	private String dim5Value;
	@Column( name = "dim_6_value" )
	private String dim6Value;
	@Column( name = "dim_7_value" )
	private String dim7Value;
	@Column( name = "dim_8_value" )
	private String dim8Value;
	@Column( name = "dim_9_value" )
	private String dim9Value;
	@Column( name = "dim_10_value" )
	private String dim10Value;
	public String getDim1Name() {
		return dim1Name;
	}

	public void setDim1Name(String dim1Name) {
		this.dim1Name = dim1Name;
	}

	public String getDim2Name() {
		return dim2Name;
	}

	public void setDim2Name(String dim2Name) {
		this.dim2Name = dim2Name;
	}

	public String getDim3Name() {
		return dim3Name;
	}

	public void setDim3Name(String dim3Name) {
		this.dim3Name = dim3Name;
	}

	public String getDim4Name() {
		return dim4Name;
	}

	public void setDim4Name(String dim4Name) {
		this.dim4Name = dim4Name;
	}

	public String getDim5Name() {
		return dim5Name;
	}

	public void setDim5Name(String dim5Name) {
		this.dim5Name = dim5Name;
	}

	public String getDim6Name() {
		return dim6Name;
	}

	public void setDim6Name(String dim6Name) {
		this.dim6Name = dim6Name;
	}

	public String getDim7Name() {
		return dim7Name;
	}

	public void setDim7Name(String dim7Name) {
		this.dim7Name = dim7Name;
	}

	public String getDim8Name() {
		return dim8Name;
	}

	public void setDim8Name(String dim8Name) {
		this.dim8Name = dim8Name;
	}

	public String getDim9Name() {
		return dim9Name;
	}

	public void setDim9Name(String dim9Name) {
		this.dim9Name = dim9Name;
	}

	public String getDim10Name() {
		return dim10Name;
	}

	public void setDim10Name(String dim10Name) {
		this.dim10Name = dim10Name;
	}

	public String getDim1Value() {
		return dim1Value;
	}

	public void setDim1Value(String dim1Value) {
		this.dim1Value = dim1Value;
	}

	public String getDim2Value() {
		return dim2Value;
	}

	public void setDim2Value(String dim2Value) {
		this.dim2Value = dim2Value;
	}

	public String getDim3Value() {
		return dim3Value;
	}

	public void setDim3Value(String dim3Value) {
		this.dim3Value = dim3Value;
	}

	public String getDim4Value() {
		return dim4Value;
	}

	public void setDim4Value(String dim4Value) {
		this.dim4Value = dim4Value;
	}

	public String getDim5Value() {
		return dim5Value;
	}

	public void setDim5Value(String dim5Value) {
		this.dim5Value = dim5Value;
	}

	public String getDim6Value() {
		return dim6Value;
	}

	public void setDim6Value(String dim6Value) {
		this.dim6Value = dim6Value;
	}

	public String getDim7Value() {
		return dim7Value;
	}

	public void setDim7Value(String dim7Value) {
		this.dim7Value = dim7Value;
	}

	public String getDim8Value() {
		return dim8Value;
	}

	public void setDim8Value(String dim8Value) {
		this.dim8Value = dim8Value;
	}

	public String getDim9Value() {
		return dim9Value;
	}

	public void setDim9Value(String dim9Value) {
		this.dim9Value = dim9Value;
	}

	public String getDim10Value() {
		return dim10Value;
	}

	public void setDim10Value(String dim10Value) {
		this.dim10Value = dim10Value;
	}

	private void setDimName(int dimNum, String value) {
		try {
			if ((dimNum < 1) || (dimNum > MAX_DIM_NUM)) {
				throw new IllegalArgumentException("No such method");
			}
			Method m = this.getClass().getMethod("setDim"+dimNum+"Name", String.class);
			m.invoke(this, value);
		} catch (Exception ex) {
			LOG.error("Unable to invoke setDim"+dimNum+"Name, method may not exist");
			LOG.error(ex,ex);
		}
	}

	private void setDimValue(int dimNum, String value) {
		try {
			if ((dimNum < 1) || (dimNum > MAX_DIM_NUM)) {
				throw new IllegalArgumentException("No such method");
			}
			Method m = this.getClass().getMethod("setDim"+dimNum+"Value", String.class);
			m.invoke(this, value);
		} catch (Exception ex) {
			LOG.error("Unable to invoke setDim"+dimNum+"Value, method may not exist");
			LOG.error(ex,ex);
		}
	}

	private String getDimName(int dimNum) {
		try {
			if ((dimNum < 1) || (dimNum > MAX_DIM_NUM)) {
				throw new IllegalArgumentException("No such method");
			}
			Method m = this.getClass().getMethod("getDim"+dimNum+"Name");
			return (String) m.invoke(this);
		} catch (Exception ex) {
			LOG.error("Unable to invoke setDim"+dimNum+"Name, method may not exist");
			LOG.error(ex,ex);
			return null;
		}
	}

	private String getDimValue(int dimNum) {
		try {
			if ((dimNum < 1) || (dimNum > MAX_DIM_NUM)) {
				throw new IllegalArgumentException("No such method");
			}
			Method m = this.getClass().getMethod("getDim"+dimNum+"Value");
			return (String) m.invoke(this);
		} catch (Exception ex) {
			LOG.error("Unable to invoke setDim"+dimNum+"Value, method may not exist");
			LOG.error(ex,ex);
			return null;
		}
	}
}
