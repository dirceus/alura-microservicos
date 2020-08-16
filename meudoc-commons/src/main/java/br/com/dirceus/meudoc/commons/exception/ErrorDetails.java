package br.com.dirceus.meudoc.commons.exception;

import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import java.util.Collections;

public class ErrorDetails {

	private String mensagem;
	private List<String> erros;
	private int status;
	private Date data;
	private StackTraceElement[] detalhes;

	public ErrorDetails(){
		
	}
	
	private ErrorDetails(Builder builder) {
		this.mensagem = builder.mensagem;
		this.erros = builder.erros;
		this.status = builder.status;
		this.data = builder.data;
		this.detalhes = builder.detalhes;
	}

	
	public String getMensagem() {
		return mensagem;
	}
	
	public List<String> getErros() {
		return erros;
	}

	public int getStatus() {
		return status;
	}

	public Date getData() {
		return data;
	}

	public StackTraceElement[]  getDetalhes() {
		return detalhes;
	}

	/**
	 * Creates builder to build {@link ErrorDetails}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link ErrorDetails}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String mensagem;
		private List<String> erros = Collections.emptyList();
		private int status;
		private Date data;
		private StackTraceElement[] detalhes;

		private Builder() {
		}

		public Builder withMensagem(String mensagem) {
			this.mensagem = mensagem;
			return this;
		}

		public Builder withErros(List<String> erros) {
			this.erros = erros;
			return this;
		}

		public Builder withStatus(int status) {
			this.status = status;
			return this;
		}

		public Builder withData(Date data) {
			this.data = data;
			return this;
		}

		public Builder withDetalhes(StackTraceElement[] detalhes) {
			this.detalhes = detalhes;
			return this;
		}

		public ErrorDetails build() {
			return new ErrorDetails(this);
		}
	}

	

	
	
	

	
	
	
}
