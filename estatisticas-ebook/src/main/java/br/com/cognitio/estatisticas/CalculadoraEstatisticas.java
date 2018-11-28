package br.com.cognitio.estatisticas;

import java.text.Normalizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.Plugin;

public class CalculadoraEstatisticas implements Plugin {

	public String cssDoTema() {
		return null;
	}

	public void aposGeracao(Ebook ebook) {

		for (Capitulo capitulo : ebook.getCapitulos()) {
			String html = capitulo.getConteudoHTML();
			Document doc = Jsoup.parse(html);
			String textoDoCapitulo = doc.body().text();

			String textoDoCapituloSemPontuacao = textoDoCapitulo.replaceAll("\\p{Punct}", " ");
			String textoDoCapituloSemAcentos = Normalizer.normalize(textoDoCapituloSemPontuacao, Normalizer.Form.NFD)
					.replaceAll("[^\\p{ASCII}]", "");

			String[] palavras = textoDoCapituloSemAcentos.split("\\s+");
			for (String palavra : palavras) {
				String emMaiusculas = palavra.toUpperCase();
				System.out.println(emMaiusculas);
			}
		}

	}

}
