package br.com.dirceus.auth.common.exception;

public class ExceptionDetails {

	private String mensagem;
	private int status;
	private long timestamp;
	private StackTraceElement[] detalhes;

	private ExceptionDetails(Builder builder) {
		this.mensagem = builder.mensagem;
		this.status = builder.status;
		this.timestamp = builder.timestamp;
		this.detalhes = builder.detalhes;
	}

	private ExceptionDetails() {
		
	}

	
	public String getMensagem() {
		return mensagem;
	}

	public int getStatus() {
		return status;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public StackTraceElement[]  getDetalhes() {
		return detalhes;
	}


	/**
	 * Creates builder to build {@link ExceptionDetails}.
	 * @return created builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link ExceptionDetails}.
	 */
	public static final class Builder {
		private String mensagem;
		private int status;
		private long timestamp;
		private StackTraceElement[] detalhes;

		private Builder() {
		}

		public Builder withMensagem(String mensagem) {
			this.mensagem = mensagem;
			return this;
		}

		public Builder withStatus(int status) {
			this.status = status;
			return this;
		}

		public Builder withTimestamp(long timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public Builder withDetalhes(StackTraceElement[] detalhes) {
			this.detalhes = detalhes;
			return this;
		}

		public ExceptionDetails build() {
			return new ExceptionDetails(this);
		}
	}

	
	
}
