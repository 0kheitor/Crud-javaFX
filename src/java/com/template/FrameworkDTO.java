package com.template;

import java.util.Scanner;

public class FrameworkDTO {

    /**
     * *
     * QUANTO AO DESIGN DE DTO... Um problema surgiu a cerca do seguinte
     * aspecto: "Como controlar os IDs de modo a impedir que o dev pudesse mudar
     * após cada inserção?"
     *
     * Minha escolha, na verdade, foi justamente agarrar essa ideia e mudar a
     * filosofia da class DTO, ela seria "stateless", não é porque voce usou uma
     * vez que tal instancia SEMPRE sera atrelada a tal linha do postgres.
     *
     * exemplo:
     *
     * meuDto.setAllMenu(); //função descrita nesse mesmo arquivo la embaixo
     * meuDao.postFramework(meuDto);
     *
     *   //eu posso muito bem reutilizar
     *
     * meuuDto.setAllMenu(); //DENOVO e para OUTRA linha do BD meuDao.post...
     * enfim...
     */

    private int id;
    private String name;
    private String tecnology;
    private String projectType;
    private String highestVersion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTecnology() {
        return tecnology;
    }

    public void setTecnology(String tecnology) {
        this.tecnology = tecnology;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projecttType) {
        this.projectType = projecttType;
    }

    public String getHighestVersion() {
        return highestVersion;
    }

    public void setHighestVersion(String highestVersion) {
        this.highestVersion = highestVersion;
    }

    public void setAllMenu() {

        /**
         * Essa funcao cria uma mini interface de entrada de dados,
         * desse modo, nao preciso
         * fazer isso no main.
         */

        Scanner input = new Scanner(System.in);
        System.out.println("--DEFINICAO DO DTO--");

        System.out.print("Id: ");
        this.id = input.nextInt();
        input.nextLine();

        System.out.print("Name: ");
        this.name = input.nextLine();

        System.out.print("Tecnology: ");
        this.tecnology = input.nextLine();

        System.out.print("Project Type: ");
        this.projectType = input.nextLine();

        System.out.print("Highest Version: ");
        this.highestVersion = input.nextLine();
    }
}