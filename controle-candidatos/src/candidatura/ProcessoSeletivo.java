package candidatura;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/*
 * Case 1: Em uma empresa existe o valor base salarial de 2000 reais. A empresa
 * fará um processo seletivo onde perguntará o salário pretendido pelo
 * candidato. 
 * -> Se o valor do salário base for maior que o valor do salário
 * pretendido, imprimir: LIGAR PARA O CANDIDATO 
 * -> Se não, se o valor do salário base for igual ao valor do salário pretendido, imprimir:
 *  LIGAR PARA O CANDIDATO COM CONTRA PROPOSTA
 * -> Se não, imprimir: AGUARDANDO RESULTADO DOS DEMAIS CANDIDATOS; 
 * 
 * Case 2: O sistema precisa garantir que diante das inúmeras
 * candidaturas sejam selecionadas apenas no máximo 5 candidatos para a
 * entrevista onde o salário pretendido seja menor ou igual ao salário base.
 * 
 * Case 3: Imprimir a lista dos candidatos selecionados para disponibilizar para
 *  o RH entrar em contato.
 *  
 * Case 4: O RH deverá realizar uma ligação com no máximo 3 tentativas para cada 
 * candidato selecionado e caso o candidato atenta, deve-se imprimir:
 * -> CONSEGUIMOS CONTATO COM O [CANDIDATO] APÓS [TENTATIVAS]
 * caso contrário, imprima:
 * -> NÃO CONSEGUIMOS CONTATO COM O [CANDIDATO]
 */

public class ProcessoSeletivo {

	public static void main(String[] args) {
		selecaoCandidatos();
	}

	static void selecaoCandidatos() {
		// Array com a lista de candidatos
		String[] candidatos = { "FELIPE", "MARCIA", "JULIA", "PAULO", "AUGUSTO", "MONICA", "FABRICIO", "MIRELA",
				"DANIELA", "JORGE" };
		String[] candidatosAprovados = new String[5];
		int candidatosSelecionados = 0;
		int candidatoAtual = 0;
		double salarioBase = 2000.0;

		while (candidatosSelecionados < 5 && candidatoAtual < candidatos.length) {
			String candidato = candidatos[candidatoAtual];
			double salarioPretendido = valorPretendido();
			System.out.printf("O candidato %s solicitou o valor R$ %.2f de salário\n", candidato, salarioPretendido);

			if (salarioBase >= salarioPretendido) {
				System.out.printf("O candidato %s foi selecionado para a vaga\n", candidato);
				candidatosAprovados[candidatosSelecionados] = candidato;
				candidatosSelecionados++;
			} else {
				System.out.printf("O candidato %s não foi selecionado para a vaga\n", candidato);
			}
			candidatoAtual++;
		}
		imprimirSelecionados(candidatosAprovados);
		entrarEmContato(candidatosAprovados);
	}

	// Método que simula o valor pretendido
	static double valorPretendido() {
		return ThreadLocalRandom.current().nextDouble(1800, 2150);
	}

	static void imprimirSelecionados(String[] candidatos) {
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("LISTA DE CANDIDATOS SELECIONADOS");
		for (String candidato : candidatos) {
			System.out.println(candidato);
		}
	}

	static void entrarEmContato(String[] candidatos) {
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("INICIANDO CONTATO COM CANDIDATOS SELECIONADOS");
		for (String candidato : candidatos) {
			int tentativasRealizadas = 1;
			boolean continuarTentando = true;
			boolean atendeu = false;

			do {
				atendeu = atender();
				continuarTentando = !atendeu;
				if (continuarTentando) {
					tentativasRealizadas++;
				} else {
					System.out.println("CONTATO REALIZADO COM SUCESSO");
				}
			} while (continuarTentando && tentativasRealizadas < 3);

			if (atendeu) {
				System.out
						.println("CONSEGUIMOS CONTATO COM " + candidato + " NA " + tentativasRealizadas + " TENTATIVA");
			} else {
				System.out.println("NÃO CONSEGUIMOS CONTATO COM " + candidato + " NÚMERO MÁXIMO DE TENTATIVAS: "
						+ tentativasRealizadas + " REALIZADAS");
			}
		}
	}

	// Método para simular se o candidato atendeu o telefone
	static boolean atender() {
		return new Random().nextInt(3) == 1;
	}

	static void analisarCandidato(double salarioPretendido) {
		double salarioBase = 2000.0;
		if (salarioBase > salarioPretendido) {
			System.out.println("LIGAR PARA O CANDIDATO");
		} else if (salarioBase == salarioPretendido) {
			System.out.println("LIGAR PARA O CANDIDATO COM CONTRA PROPOSTA");
		} else {
			System.out.println("AGUARDANDO RESULTADO DOS DEMAIS CANDIDATOS");
		}
	}
}
