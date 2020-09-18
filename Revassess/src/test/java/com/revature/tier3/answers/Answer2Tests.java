package com.revature.tier3.answers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.dialect.internal.StandardDialectResolver;
import org.hibernate.engine.jdbc.dialect.spi.DatabaseMetaDataDialectResolutionInfoAdapter;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolver;
import org.hibernate.jdbc.ReturningWork;
import org.junit.jupiter.api.extension.ExtendWith;

import com.revature.config.ConnectionUtil;
import com.revature.config.TestConfig;

import dev.ranieri.assesors.RevAssess;
import dev.ranieri.assesors.RevaTest;

/**
 * prompt: 
 * Create a sequence that produces 
 * numbers beginning at 6 and increments
 * by 3 each time.
 * 
 */
@ExtendWith(RevAssess.class)
public class Answer2Tests {

    @RevaTest(tier = 3, points = 20)
    public void testSequence() {
        try{
        String sequence = ConnectionUtil.TIER_3_SEQUENCE_NAME;
        assertEquals(0, getSequenceResult(sequence)%3);
        assertEquals(1, (getSequenceResult(sequence)+1)%3);
        } catch(Exception e){
            fail();
        }
    }

    public long getSequenceResult(final String sequenceName) {
        ReturningWork<Long> sequenceWork = new ReturningWork<Long>() {
            @Override
            public Long execute(Connection connection) throws SQLException {
                DialectResolver dialectResolver = new StandardDialectResolver();
                Dialect dialect = dialectResolver.resolveDialect(new DatabaseMetaDataDialectResolutionInfoAdapter(connection.getMetaData()));
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                try {
                    preparedStatement = connection.prepareStatement( dialect.getSequenceNextValString(sequenceName));
                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                    return resultSet.getLong(1);
                }catch (SQLException e) {
                    throw e;
                } finally {
                    if(preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if(resultSet != null) {
                        resultSet.close();
                    }
                }

            }
        };
        return TestConfig.getInstance().openSession().doReturningWork(sequenceWork);

    }
    

}