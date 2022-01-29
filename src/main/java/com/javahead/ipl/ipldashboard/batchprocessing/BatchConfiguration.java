package com.javahead.ipl.ipldashboard.batchprocessing;

import com.javahead.ipl.ipldashboard.data.MatchDataInput;
import com.javahead.ipl.ipldashboard.model.Match;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    public final String[] FIELD_NAMES = new String[]{"id","season","city","date","team1","team2","toss_winner","toss_decision","result","dl_applied","winner","win_by_runs","win_by_wickets","player_of_match","venue","umpire1","umpire2","umpire3"};

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<MatchDataInput> reader() {
        return new FlatFileItemReaderBuilder<MatchDataInput>()
                .name("matchDataItemReader")
                .resource(new ClassPathResource("matches.csv"))
                .delimited()
                .names(FIELD_NAMES)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchDataInput>() {
                    {
                      setTargetType(MatchDataInput.class);
                    }
                })
                .build();
    }

    @Bean
    public MatchDataItemProcessor processor() {
        return new MatchDataItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Match>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO match (id,season,city,date,team1,team2,toss_winner,toss_decision,result,winner,win_by_runs,win_by_wickets, player_of_match,venue,umpire1,umpire2,umpire3) " +
                                "VALUES (:id,:season,:city,:date,:team1,:team2,:tossWinner,:tossDecision,:result,:winner,:winByRuns,:winByWickets,:playerOfMatch,:venue,:umpire1,:umpire2,:umpire3)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Match> writer) {
        return stepBuilderFactory.get("step1")
                .<MatchDataInput, Match> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }
}