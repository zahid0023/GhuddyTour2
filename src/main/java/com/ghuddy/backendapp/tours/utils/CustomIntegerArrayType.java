package com.ghuddy.backendapp.tours.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;

public class CustomIntegerArrayType implements UserType {
    /**
     * @return
     */
    @Override
    public int[] sqlTypes() {
        return new int[]{Types.ARRAY};
    }

    /**
     * @return
     */
    @Override
    public Class returnedClass() {
        return Integer[].class;
    }

    /**
     * @param o
     * @param o1
     * @return
     * @throws HibernateException
     */
    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        if (o == o1) return true;
        if (o == null || o1 == null) return false;

        return o.equals(o1);
    }

    /**
     * @param o
     * @return
     * @throws HibernateException
     */
    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    /**
     * @param resultSet
     * @param strings
     * @param sharedSessionContractImplementor
     * @param o
     * @return
     * @throws HibernateException
     * @throws SQLException
     */
    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        // Assuming the column name is stored in the strings array
        String columnName = strings[0];

        // Retrieve the array from the ResultSet
        Integer[] array = (Integer[]) resultSet.getArray(columnName).getArray();

        // If the array is null, return an empty array or handle it as needed
        if (array == null) {
            return new Integer[0];
        }

        return Arrays.copyOf(array, array.length);
    }

    /**
     * @param preparedStatement
     * @param value
     * @param index
     * @param sharedSessionContractImplementor
     * @throws HibernateException
     * @throws SQLException
     */
    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        if (value == null) {
            preparedStatement.setNull(index, Types.ARRAY);
        } else {
            int[] array = (int[]) value;

            // Convert int[] to Integer[]
            Integer[] integerArray = new Integer[array.length];
            for (int i = 0; i < array.length; i++) {
                integerArray[i] = array[i];
            }

            // using PostgreSQL array
            preparedStatement.setArray(index, sharedSessionContractImplementor.connection().createArrayOf("integer", integerArray));
        }
    }


    /**
     * @param o
     * @return
     * @throws HibernateException
     */
    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return null;
    }

    /**
     * @return
     */
    @Override
    public boolean isMutable() {
        return false;
    }

    /**
     * @param o
     * @return
     * @throws HibernateException
     */
    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return null;
    }

    /**
     * @param serializable
     * @param o
     * @return
     * @throws HibernateException
     */
    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return null;
    }

    /**
     * @param o
     * @param o1
     * @param o2
     * @return
     * @throws HibernateException
     */
    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return null;
    }
}

