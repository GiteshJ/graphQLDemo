package com.example.graphQLDemo.resolver.bank.mutation;

import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UploadFileMutationResolver implements GraphQLMutationResolver {

  public UUID uploadFile(DataFetchingEnvironment environment){
    log.info("Uploading file");
    DefaultGraphQLServletContext context  = environment.getContext();
    context.getFileParts().forEach(
        part -> log.info("uploading: {} , size: {}", part.getSubmittedFileName(), part.getSize()));
    return UUID.randomUUID();
  }
}
