package br.edu.ufcg.dsc.si.blog.webservice;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import ourExceptions.ArgumentInvalidException;

public interface BlogWS {



        @WebMethod
        public void createProfile(@WebParam(name = "login")
        String login, @WebParam(name = "senha")
        String senha, @WebParam(name = "nome_exibicao")
        String nome_exibicao, @WebParam(name = "email")
        String email, @WebParam(name = "sexo")
        String sexo, @WebParam(name = "dataNasc")
        String dataNasc, @WebParam(name = "endereco")
        String endereco, @WebParam(name = "interesses")
        String interesses, @WebParam(name = "quem_sou_eu")
        String quem_sou_eu, @WebParam(name = "filmes")
        String filmes, @WebParam(name = "musicas")
        String musicas, @WebParam(name = "livros")
        String livros) throws Exception;

        @WebMethod
        public String getProfileInformation(@WebParam(name = "login")
        String login, @WebParam(name = "atributo")
        String atributo) throws Exception;

        @WebMethod
        public String logon(@WebParam(name = "login")
        String login, @WebParam(name = "senha")
        String senha) throws Exception;

        @WebMethod
        public Boolean isUserLogged(@WebParam(name = "login")
        String login) throws Exception;

        @WebMethod
        public String getProfileInformationBySessionId(
                        @WebParam(name = "sessionId")
                        String sessionId, @WebParam(name = "atributo")
                        String atributo) throws Exception;

        @WebMethod
        public void logoff(@WebParam(name = "sessionId")
        String sessionId) throws Exception;

        @WebMethod
        public void changeProfileInformation(@WebParam(name = "sessionId")
        String sessionId, @WebParam(name = "atributo")
        String atributo, @WebParam(name = "valor") String valor) throws Exception;

        @WebMethod
        public String createBlog(@WebParam(name = "sessionId")
        String sessionId, @WebParam(name = "titulo")
        String titulo, @WebParam(name = "descricao")
        String descricao) throws Exception;

        @WebMethod
        public String getBlogInformation(@WebParam(name = "BlogId")
        String BlogId, @WebParam(name = "atributo")
        String atributo) throws Exception;

        @WebMethod
        public void changeBlogInformation(@WebParam(name = "sessionId")
        String sessionId, @WebParam(name = "BlogId")
        String BlogId, @WebParam(name = "atributo")
        String atributo, @WebParam(name = "valor")
        String valor) throws Exception;

        @WebMethod
        public String createPost(@WebParam(name = "sessionId")
        String sessionId, @WebParam(name = "blogId")
        String blogId, @WebParam(name = "titulo")
        String titulo, @WebParam(name = "texto")
        String texto) throws Exception;

        @WebMethod
        public String attachSound(@WebParam(name = "sessionId")
        String sessionId, @WebParam(name = "postId")
        String postId, @WebParam(name = "descricao")
        String descricao, @WebParam(name = "dado")
        String dado) throws Exception;

        @WebMethod
        public String attachMovie(@WebParam(name = "sessionId")
        String sessionId, @WebParam(name = "postId")
        String postId, @WebParam(name = "descricao")
        String descricao, @WebParam(name = "dado")
        String dado) throws Exception;

        @WebMethod
        public String attachPicture(@WebParam(name = "sessionId")
        String sessionId, @WebParam(name = "postId")
        String postId, @WebParam(name = "descricao")
        String descricao, @WebParam(name = "dado")
        String dado) throws Exception;

        @WebMethod
        public String getPostInformation(@WebParam(name = "postId")
        String postId, @WebParam(name = "atributo")
        String atributo) throws Exception;

        @WebMethod
        public Integer getNumberOfSounds(@WebParam(name = "postId")
        String postId);

        @WebMethod
        public Integer getNumberOfMovies(@WebParam(name = "postId")
        String postId);

        @WebMethod
        public Integer getNumberOfPictures(@WebParam(name = "postId")
        String postId);

        @WebMethod
        public String getSound(@WebParam(name = "postId")
        String postId, @WebParam(name = "index")
        Integer index) throws Exception;

        @WebMethod
        public String getSoundDescription(@WebParam(name = "soundId")
        String soundId) throws Exception;

        @WebMethod
        public String getSoundData(@WebParam(name = "soundId")
        String soundId) throws Exception;

        @WebMethod
        public String getMovie(@WebParam(name = "postId")
        String postId, @WebParam(name = "index")
        Integer index) throws Exception;

        @WebMethod
        public String getMovieDescription(@WebParam(name = "movieId")
        String soundId) throws Exception;

        @WebMethod
        public String getMovieData(@WebParam(name = "dataId")
        String soundId) throws Exception;

        @WebMethod
        public String getPicture(@WebParam(name = "postId")
        String postId, @WebParam(name = "index")
        Integer index) throws Exception;

        @WebMethod
        public String getPictureDescription(@WebParam(name = "pictureId")
        String soundId) throws Exception;

        @WebMethod
        public String getPictureData(@WebParam(name = "pictureId")
        String soundId) throws Exception;

        @WebMethod
        public Integer getNumberOfBlogsByLogin(@WebParam(name = "login")
        String login) throws Exception;

        @WebMethod
        public Integer getNumberOfBlogsBySessionId(@WebParam(name = "sessionId")
        String sessiongId) throws Exception;

        @WebMethod
        public Integer getBlogBySessionId(@WebParam(name = "sessionId")
        String sessiongId, @WebParam(name = "index")
        Integer index) throws Exception;

        @WebMethod
        public Integer getBlogByLogin(@WebParam(name = "login")
        String login, @WebParam(name = "index")
        Integer index) throws Exception;

        @WebMethod
        public Integer getNumberOfPosts(@WebParam(name = "blogId")
        String blogId) throws Exception;

        @WebMethod
        public Integer getPost(@WebParam(name = "blogId")
        String blogId, @WebParam(name = "index")
        Integer index) throws Exception;

        @WebMethod
        public void changePostInformation(@WebParam(name = "sessionId")
        String sessionId, @WebParam(name = "postId")
        String postId, @WebParam(name = "atributo")
        String atributo, @WebParam(name = "valor")
        String valor) throws Exception;

        @WebMethod
        public void deleteMovie(@WebParam(name = "sessiondId")
        String sessionId, @WebParam(name = "videoId")
        String videoId) throws Exception;

        @WebMethod
        public void deleteSound(@WebParam(name = "sessiondId")
        String sessionId, @WebParam(name = "soundId")
        String soundId) throws Exception;

        @WebMethod
        public void deletePicture(@WebParam(name = "sessiondId")
        String sessionId, @WebParam(name = "pictureId")
        String pictureId) throws Exception;

        @WebMethod
        public String addComment(@WebParam(name = "sessiondId")
        String sessionId, @WebParam(name = "postId")
        String postId, @WebParam(name = "texto")
        String texto) throws Exception;

        @WebMethod
        public Integer getNumberOfComments(@WebParam(name = "postId")
        String postId) throws Exception;

        @WebMethod
        public String getComment(@WebParam(name = "postId")
        String postId, @WebParam(name = "index")
        Integer index) throws Exception;

        @WebMethod
        public String getCommentText(@WebParam(name = "commentId")
        String commentId) throws Exception;

        @WebMethod
        public String getCommentAuthor(@WebParam(name = "commentId")
        String commentId) throws Exception;

        @WebMethod
        public void deletePost(@WebParam(name = "sessiondId")
        String sessionId, @WebParam(name = "postId")
        String postId) throws Exception;

        @WebMethod
        public void deleteBlog(@WebParam(name = "sessiondId")
        String sessionId, @WebParam(name = "blogId")
        String blogId) throws Exception;

        @WebMethod
        public void deleteProfile(@WebParam(name = "sessiondId")
        String sessionId) throws Exception;

        @WebMethod
        public String findProfileByName(@WebParam(name = "match")
        String match);

        @WebMethod
        public String findProfileByInterests(@WebParam(name = "match")
        String match);

        @WebMethod
        public String findProfileByGender(@WebParam(name = "match")
        String match);

        @WebMethod
        public String findBlogByName(@WebParam(name = "match")
        String match);

        @WebMethod
        public Integer getNumberOfAnnouncements(@WebParam(name = "sessiondId")
        String sessionId) throws Exception;

        @WebMethod
        public void addPostAnnouncements(@WebParam(name = "sessiondId")
        String sessionId, @WebParam(name = "blogId")
        String blogId) throws Exception;

        @WebMethod
        public String getAnnouncement(@WebParam(name = "sessiondId")
        String sessionId, @WebParam(name = "index")
        Integer index) throws Exception;

        @WebMethod
        public String getPostJustCreated(@WebParam(name = "announcementId")
        String announcementId) throws Exception;

        @WebMethod
        public void deleteAnnouncement(@WebParam(name = "sessiondId")
        String sessionId, @WebParam(name = "announcementId")
        String announcementId) throws Exception;

        @WebMethod
        public String createSubBlog(@WebParam(name = "sessionId")
        String sessionId, @WebParam(name = "blogId")
        String blogId, @WebParam(name = "titulo")
        String titulo, @WebParam(name = "descricao")
        String descricao) throws Exception;

        @WebMethod
        public Integer getNumberOfSubBlogs(@WebParam(name = "blogId")
        String blogId) throws Exception;

        @WebMethod
        public Integer getNumberOfAllSubBlogs(@WebParam(name = "blogId")
        String blogId) throws Exception;

        @WebMethod
        public Integer getSubBlog(@WebParam(name = "blogId")
        String blogId, @WebParam(name = "index")
        Integer index) throws Exception;

        @WebMethod
        public String addSubComment(@WebParam(name = "sessiondId")
        String sessionId, @WebParam(name = "commentId")
        String commentId, @WebParam(name = "texto")
        String texto) throws Exception;

        @WebMethod
        public String getSubComment(@WebParam(name = "commentId")
        String commentId, @WebParam(name = "index")
        Integer index) throws Exception;

        @WebMethod
        public Integer getNumberOfSubComments(@WebParam(name = "commentId")
        String commentId) throws Exception;

        @WebMethod
        public Integer getNumberOfAllSubComments(@WebParam(name = "commentId")
        String commentId) throws Exception;

        @WebMethod
        public Integer getNumberOfAllPosts(@WebParam(name = "blogId")
        String blogId) throws ArgumentInvalidException;

}